public class XmlData {
    public String name;
    public  String secondName;
    public String age;
    public  String course;

    public XmlData(String name,String secondName,String age,String course){
        this.name=name;
        this.secondName=secondName;
        this.age =age;
        this.course = course;
    }

    public String toString() {
        return name+" "+secondName+" "+age+" "+course;
    }
}
