import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class Director extends Meneger{
    public Director(BufferedWriter writer, BufferedReader reader){
        super(writer,reader);
    }
    public void writeDB(){
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Name new student: ");
            String name = in.next();
            System.out.print("Second name new student: ");
            String secondName = in.next();
            System.out.print("Age new student: ");
            String age = in.next();
            System.out.print("Cource new student: ");
            String cource = in.next();

            writer.write("ADD "+name+" "+secondName+" "+age+" "+cource);
            writer.newLine();
            writer.flush();
            String response = reader.readLine();
            System.out.println(response);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
