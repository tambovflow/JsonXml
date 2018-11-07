package JsonXML.Color;

public enum MyColor {
    RED("\u001B[31m"),
    BLUE("\u001B[34m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    RESET("\u001B[0m");

    private String color;

    private MyColor(String color){
        this.color = color;
    }

    @Override
    public String toString(){
        return this.color;
    }
}
