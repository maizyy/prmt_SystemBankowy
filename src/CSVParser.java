import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser {
    private String path;
    private char sep;
    public ArrayList<String[]> data = new ArrayList<>();
    public CSVParser(String pathToFile, char separator) throws FileNotFoundException {
        path = pathToFile;
        sep = separator;
        parseCSV();
    }
    public boolean isAccountInDatabase(String name, String password){
        for (String[] datum : data) {
            if (name.equals(datum[1]) && password.equals(datum[2])) {
                return true;
            }
        }
        return false;
    }
    public String[] getInf(String name, String password){
        int num = numberInDatabase(name,password);
        return data.get(num);
    }
    public void parseCSV () throws FileNotFoundException {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String dat = myReader.nextLine();
                String[] d = dat.split(String.valueOf(sep));
                //System.out.println(Arrays.toString(d));
                data.add(d);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void write(){
        try {
            FileWriter writeFile = new FileWriter(path);
            data.forEach(row -> {
                StringBuilder temp = new StringBuilder();
                for (String item : row) {
                    temp.append(item).append(sep);
                }
                temp.replace(temp.length()-1,temp.length(),"\n");
                try {
                    writeFile.write(String.valueOf(temp));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writeFile.close();
        } catch (IOException e) {
            System.out.println("Oj byczku, byczku -1");
            e.printStackTrace();
        }
    }
    public int numberInDatabase(String name, String password){
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            if(name.equals(data.get(i)[1])&&password.equals(data.get(i)[2])){
                index = i;
                break;
            }
        }
        return index;
    }
    public void changeLineInDatabase(String name, String password, String[] line) throws IOException {
        int index = numberInDatabase(name,password);
        data.set(index,line);
        write();
    }
    public void deleteFromDatabase(String name, String password){
        int index = numberInDatabase(name,password);
        data.remove(index);
        write();
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        data.forEach(row -> {
            StringBuilder temp = new StringBuilder().append("[");
            for (String s : row) {
                temp.append(s).append(", ");
            }
            temp.replace(temp.length()-2,temp.length()-1,"]\n");
            res.append(temp);
        });
        return res.toString();
    }

    public static void main(String[] args) {

    }
}
