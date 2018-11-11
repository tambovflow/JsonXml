package JsonXML.ReflectionJsonXml;

import JsonXML.Annotation.IsClass;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestClass2 {
    int x =10;
    @IsClass
    TestClass testClass = new TestClass();

     String s = "JagaJuga";
    private boolean v;
    @IsClass
    TestClass secondClass = new TestClass();
    List<TestClass> list = Arrays.asList(secondClass, testClass);
    String type = list.getClass().getTypeName();
    boolean tu = Collection.class.isInstance(list);
    int [] lk = {1,2,3,45,5,5,5,6};
    boolean adf = Array.class.isInstance(lk);
    String mesadf = type.getClass().getSimpleName();
    String arasdfa = lk.getClass().getSimpleName();
}
