package JsonXML.ReflectionJsonXml;

import JsonXML.Annotation.HideField;

public class TestClass {
    private int f;
    private String st;
    private boolean t;
    @HideField
    public String string = "ads";
    int m = 1;
}
