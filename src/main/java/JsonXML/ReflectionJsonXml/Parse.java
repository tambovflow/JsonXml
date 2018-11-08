package JsonXML.ReflectionJsonXml;

import JsonXML.Annotation.HideField;
import JsonXML.Annotation.IsClass;
import JsonXML.Color.MyColor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

public class Parse {
    private  String yellow = "";
    private  String green = "";
    private  String red = "";
    private  String blue = "";
    private  String reset = "";
    private  String t = "\t";
    private StringBuilder sbJson = new StringBuilder();
    private StringBuilder sbXML = new StringBuilder();

    public  String toJson(Object object) {
        String name = null;
        Object value = null;

        String d = ",";
        Class<?> cl = object.getClass();
        Field[] fields = cl.getDeclaredFields();

        sbJson.append("{\n");

        for(int i = 0;i<fields.length; i++){
            if(i ==fields.length-1) {
                d= "";
            }
            if(fields[i].isAnnotationPresent(HideField.class)) continue;
            try {

                if(!fields[i].isAccessible()){
                    fields[i].setAccessible(true);
                }
                value = fields[i].get(object);
                name = fields[i].getName();
            } catch (IllegalAccessException ex){
                ex.fillInStackTrace();
            }
            if(fields[i].isAnnotationPresent(IsClass.class)){
                sbJson.append(t + "\"" + green + name + reset + "\" : ");
                t+="\t";
                toJson(value);
                t= "\t";
                sbJson.append(d + "\n");
                continue;
            }
                sbJson.append(t + "\"" + green + name + reset + "\" : \"" + yellow + value + reset + "\"" + d + "\n");
        }
        sbJson.append("}");
        return sbJson.toString();
    }

    public  String toJsonWithColor(Object object){
        green = MyColor.GREEN.toString();
        yellow = MyColor.YELLOW.toString();
        reset = MyColor.RESET.toString();
        if(object instanceof Collection){
            return toJson((List) object);
        }
            return toJson(object);
    }

    public String toJson(List col){
        StringBuilder sb = new StringBuilder();
        String d = ",";
        sb.append("{");
        for(int i= 0; i<col.size(); i++){
            Class<?> cl = col.get(i).getClass();
            sb.append("\n\"" + green + cl.getSimpleName() + (i+1) + reset + "\" : ");
            if(i == col.size()-1) d = "";
            String st = this.toJson(col.get(i));
            sb.append(st + d);
        }
        sb.append("}");
        return sb.toString();
    }

    public String toXml(Object object) {
        String name = null;
        Object value = null;
        Class<?> cl = object.getClass();
        Field[] fields = cl.getDeclaredFields();

        sbXML.append("<" + red + cl.getSimpleName() + reset + ">\n");
        for(Field field : fields){
        if(field.isAnnotationPresent(HideField.class)) continue;
            try {
                if(!field.isAccessible()){
                    field.setAccessible(true);
                }
                name = field.getName();
                value = field.get(object);

            } catch (IllegalAccessException ex){
                ex.fillInStackTrace();
            }
            if(field.isAnnotationPresent(IsClass.class)){
                t+="\t";
                toXml(value);
                t= "\t";
                continue;
            }
            sbXML.append(t + "<" + red + name + reset + ">" + blue + value + reset + "</" + red + name + reset + ">\n");
        }
        sbXML.append("</" + red + cl.getSimpleName() + reset + ">\n");
        return sbXML.toString();
    }

    public String toXmlWithColor(Object object){
        red = MyColor.RED.toString();
        blue = MyColor.BLUE.toString();
        reset = MyColor.RESET.toString();

        if(object instanceof Collection){
            return toXml((List) object);
        }
        return toXml(object);
    }

    public String toXml(List col){
        StringBuilder sbXML = new StringBuilder();
        sbXML.append("<" + red + "XML_SAMPLE" + reset +">\n");
        for(Object c : col){
            String st = this.toXml(c);
            sbXML.append(st);
        }
        sbXML.append("</" + red + "XML_SAMPLE" +reset + ">");
        return sbXML.toString();
    }
}