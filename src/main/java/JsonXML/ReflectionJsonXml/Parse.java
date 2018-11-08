package JsonXML.ReflectionJsonXml;

import JsonXML.Color.MyColor;
import java.lang.reflect.Field;
import java.util.Collection;

public class Parse {
    private  String yellow = "";
    private  String green = "";
    private  String red = "";
    private  String blue = "";
    private  String reset = "";

    public  String toJson(Object object) {
        String name = null;
        Object value = null;
        StringBuilder sb = new StringBuilder();
        String d = ",";
        int i =0;
        Class<?> cl = object.getClass();
        Field[] fields = cl.getDeclaredFields();

        sb.append("{\n");

        for(Field field : fields){
            if(++i ==fields.length) d= "";
            try {
                name = field.getName();
                if(!field.isAccessible()){
                    field.setAccessible(true);
                }
                value = field.get(object);
            } catch (IllegalAccessException ex){
                ex.fillInStackTrace();
            }
            sb.append("\t\"" + green + name + reset + "\" : \"" + yellow + value + reset + "\"" + d + "\n" );
        }
        sb.append("}");
        return sb.toString();
    }

    public  String toJsonWithColor(Object object){
        green = MyColor.GREEN.toString();
        yellow = MyColor.YELLOW.toString();
        reset = MyColor.RESET.toString();
        if(object instanceof Collection){
            return toJson((Collection) object);
        }
            return toJson(object);
    }

    public String toJson(Collection col){
        StringBuilder sb = new StringBuilder();
        int t = 0;
        String d = ",";
        int next = 1;
        sb.append("{");
        for(Object c : col){
            Class<?> cl = c.getClass();
            sb.append("\n\"" + green + cl.getSimpleName() + next + reset + "\" : ");
            if(++t == col.size()) d = "";
            String st = this.toJson(c);
            sb.append(st + d);
            next++;
        }
        sb.append("}");
        return sb.toString();
    }

    public String toXml(Object object) {
        StringBuilder sb = new StringBuilder();
        String name = null;
        Object value = null;
        Class<?> cl = object.getClass();
        Field[] fields = cl.getDeclaredFields();

        sb.append("<" + red + cl.getSimpleName() + reset + ">\n");
        for(Field field : fields){

            try {
                name = field.getName();
                if(!field.isAccessible()){
                    field.setAccessible(true);
                }
                value = field.get(object);
            } catch (IllegalAccessException ex){
                ex.fillInStackTrace();
            }
            sb.append("\t<" + red + name + reset + ">" + blue + value + reset + "</" + red + name + reset + ">\n");
        }
        sb.append("</" + red + cl.getSimpleName() + reset + ">\n");
        return sb.toString();
    }

    public String toXmlWithColor(Object object){

        red = MyColor.RED.toString();
        blue = MyColor.BLUE.toString();
        reset = MyColor.RESET.toString();

        if(object instanceof Collection){
            return toXml((Collection) object);
        }

        return toXml(object);
    }

    public String toXml(Collection col){
        StringBuilder sb = new StringBuilder();
        sb.append("<" + red + "XML_SAMPLE" + reset +">\n");
        for(Object c : col){
            String st = this.toXml(c);
            sb.append(st);
        }
        sb.append("</" + red + "XML_SAMPLE" +reset + ">");
        return sb.toString();
    }
}
