import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CreateAccount extends Login {
    private ArrayList<Integer> accountNumbers = new ArrayList<>();
    public CreateAccount() throws FileNotFoundException {
        getAllAccountNumbers();
    }
    private void getAllAccountNumbers() throws FileNotFoundException {
         CSVParser csv = new CSVParser(DATABASE_PATH,CSV_POINT);
         ArrayList<String[]> d= csv.data;
         for (String [] row : d){
             accountNumbers.add(Integer.parseInt(row[0]));
         }
    }

    public void create(String name,String password) throws IOException, InterruptedException {
        FileWriter fw = new FileWriter(DATABASE_PATH,true);
        Random r = new Random();
        int rand;
        int s;
        do {
            s=0;
            rand = r.nextInt(900000)+100000;
            for (Integer accountNumber : accountNumbers) {
                if (accountNumber.equals(rand)){
                    s+=1;
                }
            }
        } while(s!=0);
        accountNumbers.add(rand);

        fw.write(String.valueOf(rand) + CSV_POINT + name + CSV_POINT + password + CSV_POINT + BASE_BALANCE + CSV_POINT + BASE_INVEST + "\n");
        fw.flush();
        fw.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CreateAccount c = new CreateAccount();
        for (int i = 0; i < 1000000; i++) {
            c.create("sadasda","Dasds");
        }
        Set<Integer> set = new HashSet<Integer>(c.accountNumbers);
        System.out.println(set.size());
        System.out.println(c.accountNumbers.size());
        System.out.println(c.accountNumbers);
    }




}
