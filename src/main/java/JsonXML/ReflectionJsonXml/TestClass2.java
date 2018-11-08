package JsonXML.ReflectionJsonXml;

import JsonXML.Annotation.IsClass;

public class TestClass2 {
    int x =10;
    @IsClass
    TestClass testClass = new TestClass();
     String s = "JagaJuga";
    private boolean v;
    @IsClass
    TestClass secondClass = new TestClass();
}
