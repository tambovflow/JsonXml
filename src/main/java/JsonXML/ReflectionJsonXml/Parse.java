package JsonXML.ReflectionJsonXml;

import JsonXML.Annotation.HideField;
import JsonXML.Annotation.IsClass;
import JsonXML.Color.MyColor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;

public class Parse {
    private  String yellow = "";
    private  String green = "";
    private  String red = "";
    private  String blue = "";
    private  String reset = "";
    private  String t = "\t";
    int j =0;

    public String toJson(Object object){
        StringBuilder sbJson = new StringBuilder();
        sbJson.append("{");
        if(Collection.class.isInstance(object)){
            Collection o = (Collection) object;
            Iterator<Object> iter = o.iterator();
            while (iter.hasNext()){
                toJson(iter.next(), sbJson);
            }

        } else{
            toJson(object, sbJson);
        }
        sbJson.setLength(sbJson.length() -1);
        sbJson.append("\n}");
        return sbJson.toString();
    }

    private StringBuilder toJson(Object object, StringBuilder sbJson) {
        String name = null;
        Object value = null;
        String d = ",";
        Class<?> cl = object.getClass();
        Field[] fields = cl.getDeclaredFields();

        sbJson.append("\n" + t + "\""  + green + cl.getSimpleName() + "_" + j++ + reset + "\" : {");
            for (int i = 0; i < fields.length; i++) {
                if (i == fields.length - 1) {
                    d = "";
                }
                else if (fields[i].isAnnotationPresent(HideField.class)) continue;
                try {
                    fields[i].setAccessible(true);
                    value = fields[i].get(object);
                    name = fields[i].getName();
                } catch (IllegalAccessException ex) {
                    ex.fillInStackTrace();
                }
                if(Collection.class.isInstance(value)){
                    Collection o = (Collection) value;
                    Iterator<Object> iter = o.iterator();
                    while (iter.hasNext()){
                        toJson(iter.next(), sbJson);
                    }
                    continue;
                }
                if (fields[i].isAnnotationPresent(IsClass.class)) {
                    t+="\t";
                    toJson(value, sbJson);
                    t="\t";
                    continue;
                }
                sbJson.append("\n" + t + "\t\"" + green + name + reset + "\" : \"" + yellow + value + reset + "\"" + d);
            }
        sbJson.append("\n" + t + "},");
        return sbJson;
    }

    public String toJsonWithColor(Object object) {
        green = MyColor.GREEN.toString();
        yellow = MyColor.YELLOW.toString();
        reset = MyColor.RESET.toString();

        return toJson(object);
    }
    public String toXml(Object object){
        StringBuilder sbXML = new StringBuilder();
        sbXML.append("<" + red + "XML_SAPMLE" + reset + ">\n");
        if(Collection.class.isInstance(object)){
            Collection o = (Collection) object;
            Iterator<Object> iter = o.iterator();
            while (iter.hasNext()){
                toXml(iter.next(), sbXML);
            }
        } else {
            toXml(object, sbXML);
        }
        sbXML.append("</" + red + "XML_SAPMLE" + reset + ">");
        return sbXML.toString();
    }
    private StringBuilder toXml(Object object, StringBuilder sbXML) {
        String name = null;
        Object value = null;
        Class<?> cl = object.getClass();
        Field[] fields = cl.getDeclaredFields();

        sbXML.append(t + "<" + red + cl.getSimpleName() + reset + ">\n");

            for (Field field : fields) {
                if (field.isAnnotationPresent(HideField.class)) continue;
                try {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    name = field.getName();
                    value = field.get(object);

                } catch (IllegalAccessException ex) {
                    ex.fillInStackTrace();
                }
                if(Collection.class.isInstance(value)){
                    Collection o = (Collection) value;
                    Iterator<Object> iter = o.iterator();
                    while (iter.hasNext()){
                        toXml(iter.next(), sbXML);
                    }
                    continue;
                }
                if (field.isAnnotationPresent(IsClass.class)) {
                    t += "\t";
                    toXml(value, sbXML);
                    t = "\t";
                    continue;
                }
                sbXML.append(t  + "\t<" + red + name + reset + ">" + blue + value + reset + "</" + red + name + reset + ">\n");
            }
        sbXML.append(t + "</" + red + cl.getSimpleName() + reset + ">\n");
        return sbXML;
    }

    public String toXmlWithColor(Object object){
        red = MyColor.RED.toString();
        blue = MyColor.BLUE.toString();
        reset = MyColor.RESET.toString();

        return toXml(object);
    }
}