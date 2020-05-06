package prmt_SystemBankowy;

import java.io.*;
import java.util.*;

public class serviceMain {
    String clientId;
    String serviceId;
    double serviceValue;
    int serviceReceiver;
    int serviceOther;
    
    double accountValue; //WIKTOR ZRÓB KONTO KLIENTA TO MA BYĆ SALDO KLIENTA

    public static ArrayList<String[]> saver = new ArrayList<>();

    @Override
    public String toString(Object o){
        String s =""+o;
        return s;
    }
    /**
     * Szybka metoda zwracająca nam saldo klienta. Powinna być rozbudowana do pobierania ostatniej wartości @param accountValue z pliku klienta
     * ale Wiktor musi zrobić profil klienta.
     * @param o
     * @param accountValue
     * @return
     */
    public double accountAfterService(double o, double accountValue){
        double accountAfterService = accountValue-o;
        return accountAfterService;
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
            BufferedWriter bw = new BufferedWriter(new FileWriter(clientTransferFile));
            String[] newRequest = {toString(serviceReceiver)+";", toString(serviceValue)+";", toString(accountAfterService(serviceValue, accountValue)),";"};
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
        }else{

        }

    }
}