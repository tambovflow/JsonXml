package JsonXML.FileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile <T>{

    public List<T> createPersonList(){
        List<T> person = new ArrayList<T>();
        BufferedReader br = null;
        String string;
        try{
            InputStream is = new FileInputStream("src/main/resources/table.csv");
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            while ((string = br.readLine())!=null){
                String[] arrString = string.split(",");
                person.add((T) new Person(arrString));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }
}
