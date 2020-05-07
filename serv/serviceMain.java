package prmt_SystemBankowy;

import java.io.*;
import java.util.*;

public class serviceMain {
    String clientId;
    String serviceId;
    double serviceValue;
    int serviceReceiver;
    int servicePeriod;
    
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
    public double accountAfterTransfer(double sV, double accountValue){
        return accountValue-sV;
    }
    /**
     * Metoda procentu składanego. Na poczet tej procedury @param sV rozłożymy na dwie części, część powyżej 1 będzie określała procent lokaty 
     * a część poniżej 1 będzie określała ilość kapitalizacji w ciągu roku. Czyli, np: 3.6 określa nam lokatę 3% z kapitalizacją co dwa miesiące (12/6).
     * @param accountValue
     * @param p
     * @param sV
     * @return
     */
    public double accountAfterInvestment(double accountValue, int p, double sV){
        String sVal= ""+ sV;
        String[] investmentData =sVal.split(".");
        Int rate = Ineger.parseInt(investmentData[0]);
        Int capInterest = Integer.parseInt(investmentData[1]);
        return accountValue*((1+(rate/(100*capInterest)))^(p*capInterest));
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

    public void newInvestment(String clientId, String serviceId, double serviceValue,int servicePeriod, double accountValue){
        File clientInvestmentFile= new File(clientId+"investment");
    }
}