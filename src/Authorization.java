import java.util.*;
import java.util.concurrent.TimeUnit;

public class Authorization {


    private byte counter= 0;
    public boolean getid() throws InterruptedException {
        Scanner console = new Scanner(System.in);
        Random r = new Random();
        int pass = (r.nextInt(9000) + 1000);
        System.out.println("Twoje haslo bezpieczenstwa: "+pass);
        System.out.print("Wpisz haslo by potwierdzic: ");
        int checkPass = console.nextInt();
        byte counterLimit = 2;
        if ((checkPass == pass) && (counter < counterLimit)){
            System.out.println("Pomyslna autoryzacja !!!!");
            return true;
        }
        else if(counter >= counterLimit){
            System.out.println("Zawieszony dostep!");
            return false;
        }
        else {
            System.out.println("Sprobuj ponownie");
            counter++;
            TimeUnit.SECONDS.sleep(1);
            getid();
        }
        return true;
    }


    public static void main(String[] args) throws InterruptedException {
	    new Authorization().getid();

    }
}
