import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

 class readFile {

    public static void saveData(String file) throws FileNotFoundException, IOException {
        String line;
        FileReader f = new FileReader(file);
        BufferedReader buffer = new BufferedReader(f);
        while((line = buffer.readLine())!=null) {
            System.out.println(line);
        }
        buffer.close();
    }

    public static void main(String[] args) throws IOException {
        saveData("paris_54000.txt");
    }

}
