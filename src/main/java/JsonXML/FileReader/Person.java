package JsonXML.FileReader;

import JsonXML.Annotation.HideField;
import JsonXML.Annotation.IsClass;
import JsonXML.ReflectionJsonXml.TestClass;
import JsonXML.ReflectionJsonXml.TestClass2;

public class Person {
    private int id;
    private String serial;
    private String post;
    @HideField
    private String code;
    private int year;
    @HideField
    private String company;
    @IsClass
    TestClass2 testClass = new TestClass2();
    private String street;
    private String build;
    private String apt;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String house;

    public Person(String[] arrString){
        this.id = Integer.parseInt(arrString[0]);
        this.serial = arrString[1];
        this.post = arrString[2];
        this.code = arrString[3];
        this.year = Integer.parseInt(arrString[4]);
        this.company = arrString[5];
        this.street = arrString[6];
        this.build = arrString[7];
        this.apt = arrString[8];
        this.phoneNumber = arrString[9];
        this.firstName = arrString[10];
        this.lastName = arrString[11];
        this.patronymic = arrString[12];
        this.house = arrString[13];
    }

}
