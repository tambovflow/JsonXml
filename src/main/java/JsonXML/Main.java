package JsonXML;

import JsonXML.FileReader.Person;
import JsonXML.FileReader.ReadFile;
import JsonXML.ReflectionJsonXml.Parse;

import java.io.*;
import java.util.List;

public class Main {
    private static int t = 0;
    private static String d = ",";
    public static void main(String[] args) throws IOException {
        ReadFile<Person> rf = new ReadFile<Person>();
        List<Person> personList = rf.createPersonList();
        String path  = new File("").getAbsolutePath();
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(new File(path + "/src/main/resources/json.json")));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(new File(path + "/src/main/resources/xml.xml")));

        System.out.println(new Parse().toXmlWithColor(personList.get(1)));
        System.out.println(new Parse().toJsonWithColor(personList.get(11)));

        bw1.write(new Parse().toJson(personList));
        bw2.write(new Parse().toXml(personList));
        bw1.flush(); bw1.close();
        bw2.flush(); bw2.close();

    }

}
