package JsonXML.ReflectionJsonXml;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestClass2 {
    int x =10;
    TestClass testClass = new TestClass();
    Object ob = x;
    String s = "JagaJuga";
    private boolean v;
    TestClass secondClass = new TestClass();
    List<TestClass> list = Arrays.asList(secondClass, testClass);
    String type = list.getClass().getTypeName();
    boolean tu = Collection.class.isInstance(list);
    double [] lk = {1,2,3,45,5,5,5,6};
    Integer [] integers = new Integer[9];
    private static String be = "be";
}
