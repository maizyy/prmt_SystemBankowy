package prmt_SystemBankowy;

import java.io.*;
import java.util.*;

public class ServiceMain {
    String clientId;
    String serviceId;
    double serviceValue;
    int serviceReceiver;
    int servicePeriod;
    
    double accountValue;

    List<String[]> saver = new ArrayList<>();

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
        int rate = Integer.parseInt(investmentData[0]);
        int capInterest = Integer.parseInt(investmentData[1]);
        return accountValue*((1+(rate/(100*capInterest)))^(p*capInterest));
    }

    public void TransferWriter(File clientTransferFile, double serviceValue, int serviceReceiver, double accountValue){
        try{
            Double accountAfterService= accountAfterTransfer(serviceValue, accountValue);
            System.out.println(accountAfterService);
            if(accountAfterService>=0){
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(clientTransferFile)));
                String[] newRequest = {toString(serviceReceiver), toString(serviceValue), toString(accountAfterTransfer(serviceValue, accountValue))};
                saver.add(newRequest);
                System.out.println(newRequest[0]);
                for(int i=0; i<saver.size(); i++){
                    String def=" ";
                    String[] line = saver.get(i);
                    for(int j=0; j<3; j++){
                        def=def+line[j]+";";
                    }
                    System.out.println(def);
                    bw.write(def);
                    bw.newLine();
                }
                bw.close();
            }else{
                System.out.println("oj -1 byczku -1");
            }
        }catch(IOException e){
            System.out.println("oj -1 byczku-1");
        }
    }

    public void InvestmentWriter(File clientInvestmentFile, double serviceValue, int servicePeriod, double accountValue){
        try{
            Double accountAfterService= accountAfterInvestment(accountValue, servicePeriod, serviceValue);
            System.out.println(accountAfterService);
            if(accountAfterService>=0){
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(clientInvestmentFile)));
                String[] newRequest = {toString(serviceValue), toString(servicePeriod), toString(accountAfterService)};
                saver.add(newRequest);
                System.out.println(newRequest[0]);
                for(int i=0; i<saver.size(); i++){
                    String def=" ";
                    String[] line = saver.get(i);
                    for(int j=0; j<3; j++){
                        def=def+line[j]+";";
                    }
                    System.out.println(def);
                    bw.write(def);
                    bw.newLine();
                }
                bw.close();
            }else{
                System.out.println("oj -1 byczku -1");
            }
        }catch(IOException e){
            System.out.println("oj -1 byczku-1");
        }
    }

    /**
     * Metoda określająca wykonywanie przelewów na konto zawnętrzne.
     * @param serviceValue
     * @param serviceReceiver
     * @param accountValue
     */
    public void newTransfer(String clientId, double serviceValue, int serviceReceiver, double accountValue){
        try{
            File clientTransferFile = new File(clientId+"transfer");
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
        }catch(IOException e){
            System.out.println("oj -1 byczku-1");
        }
    }

    public void newInvestment(String clientId, double serviceValue,int servicePeriod, double accountValue){
        ArrayList<String[]> saver = new ArrayList<>();
        try{
            File clientInvestmentFile= new File(clientId+"investment");
            if((clientInvestmentFile.exists())== true){
                String testRow;
                BufferedReader br = new BufferedReader(new FileReader(clientInvestmentFile));
                while((testRow = br.readLine()) != null){
                    String[] line = testRow.split(";");
                    saver.add(line);
                }
                InvestmentWriter(clientInvestmentFile, serviceValue, servicePeriod, accountValue);
            }else{
                InvestmentWriter(clientInvestmentFile, serviceValue, servicePeriod, accountValue);
            }
        }catch(IOException e){
            System.out.println("oj -1 byczku-1");
        }
    }

    public static void main(String[] args){
        String clientId = "Najman";
        double accountValue = 60.0;
        double serviceValue = 3.4;
        int serviceReceiver = 3333;
        int servicePeriod =1;

        ServiceMain tranfer1 = new ServiceMain();

        tranfer1.newInvestment(clientId, serviceValue, servicePeriod, accountValue);

    }
}