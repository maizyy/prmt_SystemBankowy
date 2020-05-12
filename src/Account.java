import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Account extends Login {

    private String n;
    private String p;
    private String[] accountInf;
    private CSVParser csv = new CSVParser(DATABASE_PATH, CSV_POINT);
    public Account(String name, String pass1) throws IOException {
        n = name;
        p = pass1;

        init();
    }

    private void init(){
        Scanner console = new Scanner(System.in);
        accountInf = csv.getInf(n, p);
        System.out.println("Witaj "+ n +", Twój numer konta : "+accountInf[0]+"\n1 - Sprawdź stan konta\n0 - Wyloguj");
        System.out.print("Wybieram: ");
        try {
            int sw = console.nextInt();
            switch (sw) {
                case 1: {
                    String balance = accountInf[3];
                    System.out.println("Stan Konta: "+ balance);
                    init();
                    break;
                }
                case 0: {
                    starter();
                    break;
                }
            }
        }catch (Exception e){
            e.getStackTrace();
            System.err.println("oj -1 byczku -1");
        }
    }











}
