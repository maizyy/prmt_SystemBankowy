
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Account extends Login {

    public String n;
    public String p;
    public String[] accountInf;
    private CSVParser csv = new CSVParser(DATABASE_PATH, CSV_POINT);
    public Account(String name, String pass1) throws IOException {
        n = name;
        p = pass1;
        init();
    }

    private void init(){
        Scanner console = new Scanner(System.in);
        accountInf = csv.getInf(n, p);
        System.out.println("Witaj "+ n +", Twój numer konta : "+accountInf[0]+"\n1 - Sprawdź stan konta\n2 - Zrób " +
                "przelew\n3 - Lokata\n4 - Dodaj srodki na konto\n0 - Wyloguj");
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
                case 2: {
                    System.out.print("Podaj numer na który wysłać przelew: ");
                    int transferReciver = console.nextInt();//numer konta
                    System.out.print("Ilość pieniędzy : ");
                    int value = console.nextInt();//suma
                    transfer(transferReciver,value);
                    init();
                    break;
                }
                case 3: {
                    init();
                    break;
                }
                case 4: {
                    System.out.print("Dodaj środki na konto: ");
                    int addToBalance = console.nextInt();
                    changeBalance(addToBalance,'+');
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

    private void transfer(int receive, int value) throws IOException {
        changeBalance(value,'-');
        FileWriter writeFile = new FileWriter("baza"+ System.getProperty("file.separator")+accountInf[1]+"_transfer" +
                ".txt", true);
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String tmp = sdf.format(nowDate)+"\n";
        tmp += "    Przelew wysłany do: "+ receive+"\n";
        tmp += "    Kwota: "+ value+"\n\n";
        writeFile.write(tmp);
        writeFile.flush();
        writeFile.close();
        System.out.println("Przelew wykonany!!!");
    }
    private void investment(){
        System.out.println("investment");
    }

    public static void main(String[] args) throws IOException {
        new Account("Grzegorz","Iz");
    }




    private void changeBalance(int value,char operation) throws IOException {
        int tmp = Integer.parseInt(accountInf[3]);
        if (operation == '-'){
            if (tmp - value >0){
                tmp -=value;
            }
            else{
                System.out.println("Nie wystarczajace środki na koncie");
                init();
            }
        }
        else if(operation=='+'){
            tmp+= value;
        }
        accountInf[3] = String.valueOf(tmp);
        csv.changeLineInDatabase(n,p,accountInf);
    }




}
