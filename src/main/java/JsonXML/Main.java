package JsonXML;

import JsonXML.FileReader.Person;
import JsonXML.FileReader.ReadFile;
import JsonXML.ReflectionJsonXml.Parse;
import JsonXML.ReflectionJsonXml.TestClass;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadFile<Person> rf = new ReadFile<Person>();
        List<Person> personList = rf.createPersonList();
        TestClass t = new TestClass();
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(new File( "src/main/resources/json.json"))); //output JSON file
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(new File( "src/main/resources/xml.xml")));   //output XML file

        System.out.println(new Parse().toXmlWithColor(personList.get(1))); //call one class and print it in console with color
        System.out.println(new Parse().toJsonWithColor(personList.get(11)));
        System.out.println(new Parse().toJsonWithColor(t)); //without color
        System.out.println(new Parse().toXmlWithColor(t));

        bw1.write(new Parse().toJson(personList)); //write all class from collections to the file (without color)
        bw2.write(new Parse().toXml(personList));
        bw1.flush(); bw1.close();
        bw2.flush(); bw2.close();

    }

}
