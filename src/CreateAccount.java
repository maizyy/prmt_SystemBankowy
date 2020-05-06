import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateAccount extends Login {
    public void create(String name,String password) throws IOException, InterruptedException {
        FileWriter fw = new FileWriter(DATABASE_PATH,true);
        Random r = new Random();
        fw.write(String.valueOf(r.nextInt(999)) + CSV_POINT + name + CSV_POINT + password + CSV_POINT + BASE_BALANCE + CSV_POINT + BASE_INVEST + "\n");
        fw.flush();
        fw.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CreateAccount c = new CreateAccount();
        c.create("asdas","dass");
    }




}
