package prmt_SystemBankowy;

import java.io.*;
import java.util.*;

public class serviceMain {
    String clientId;
    String serviceId;
    double serviceValue;
    int serviceReceiver;
    int serviceOther;
    
    double accountValue;

    public static ArrayList<String[]> saver = new ArrayList<>();

    @Override
    public String toString(Object o){
        String s =""+o;
        return s;
    }
    
    /**
     * Szybka metoda zwracająca nam saldo klienta. Powinna być rozbudowana do pobierania ostatniej wartości @param accountValue z pliku klienta.
     * @param o
     * @param accountValue
     * @return
     */
    public double accountAfterService(double o, double accountValue){
        return accountValue-o;
    }

    public void TransferWriter(File clientTransferFile, double serviceValue, int serviceReceiver, double accountValue){
        OutputStream os = new FileOutputStream(clientTransferFile);
        BufferedWriter bw = new BufferedWriter(os);
        String[] newRequest = {toString(serviceReceiver)+";", toString(serviceValue)+";", toString(accountAfterService(serviceValue, accountValue))};
        if(accountAfterService(serviceValue, accountValue)>=0){
            saver.add(newRequest);
            String def =" ";
            for(int j=0; j<newRequest.length; j++){
                def= def+line[j]+";";
            }
            bw.write(def);
            bw.newLine();
        }else{
            throw LackOfFoundsException();
        }
    }

    /**
     * Metoda określająca wykonywanie przelewów na konto zawnętrzne.
     * @param serviceValue
     * @param serviceReceiver
     * @param accountValue
     */
    public void newTransfer(double serviceValue, int serviceReceiver, double accountValue){
        File clientTransferFile = new File(cleintId+"transfer");
        if((clientTransferFile.exists()) == true){
            String testRow;
            BufferedReader br = new BufferedReader(new FileReader(clientTransferFile));
            while ((testRow = br.readLine()) != null){
                String[] line = testRow.split(";");
                saver.add(line);
            }
            TransferWriter(clientTransferFile, serviceValue, serviceReceiver, accountValue);
        }else{
           TransferWriter(clientTransferFile, serviceValue, serviceReceiver, accountValue);
        }
    }
}