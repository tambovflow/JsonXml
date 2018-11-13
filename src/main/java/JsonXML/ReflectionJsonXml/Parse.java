package JsonXML.ReflectionJsonXml;

import JsonXML.Annotation.HideField;
import JsonXML.Color.MyColor;
import java.lang.reflect.Field;
import java.util.*;

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
        Field[] fields = object.getClass().getDeclaredFields();

        sbJson.append("\n" + t + "\""  + green + object.getClass().getSimpleName() + "_" + j++ + reset + "\" : {");
            for (int i = 0; i < fields.length; i++) {
                if (i == fields.length - 1) {
                    d = "";
                }
                if (fields[i].isAnnotationPresent(HideField.class)) continue;
                try {
                    fields[i].setAccessible(true);
                    value = fields[i].get(object);
                    name = fields[i].getName();
                } catch (IllegalAccessException ex) {
                    ex.fillInStackTrace();
                }
                if(value!=null && !(value.getClass().isPrimitive()) && !(value.getClass().toString().contains("class java")) && !(value.getClass().getTypeName().contains("[]"))){
                    t+="\t";
                    toJson(value, sbJson);
                    t="\t";
                    continue;
                }

                else if(Collection.class.isInstance(value)) {
                    Collection o = (Collection) value;
                    Iterator<Object> iter = o.iterator();
                    while (iter.hasNext()) {
                        toJson(iter.next(), sbJson);
                    }
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
        Field[] fields = object.getClass().getDeclaredFields();

        sbXML.append(t + "<" + red + object.getClass().getSimpleName() + reset + ">\n");

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
                if(value!=null && !(value.getClass().isPrimitive()) && !(value.getClass().toString().contains("class java")) && !(value.getClass().getTypeName().contains("[]"))){
                    t += "\t";
                    toXml(value, sbXML);
                    t = "\t";
                    continue;
                }
                sbXML.append(t  + "\t<" + red + name + reset + ">" + blue + value + reset + "</" + red + name + reset + ">\n");
            }
        sbXML.append(t + "</" + red + object.getClass().getSimpleName() + reset + ">\n");
        return sbXML;
    }

    public String toXmlWithColor(Object object){
        red = MyColor.RED.toString();
        blue = MyColor.BLUE.toString();
        reset = MyColor.RESET.toString();

        return toXml(object);
    }
}