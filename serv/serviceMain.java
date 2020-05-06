package prmt_SystemBankowy;

import java.io.*;
import java.util.*;

public class serviceMain {
    String clientId;
    String serviceId;
    String serviceValue;
    String serviceReceiver;
    String serviceOther;

    public static ArrayList<String[]> saver = new ArrayList<>();

    public void newTransfer(String serviceId, String serviceValue, String serviceReceiver){
        File clientTransferFile = new File(cleintId+"transfer");
        boolean exists = clientTransferFile.exists();
        if((clientTransferFile.exists()) == true){
            String testRow;
            BufferedReader br = new BufferedReader(new FileReader(clientTransferFile));
            while ((testRow = br.readLine()) != null){
                String[] line = testRow.split(";");
                saver.add(line);
            }
        }else{

        }

    }
}