import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class Meneger extends User{

    public Meneger(BufferedWriter writer, BufferedReader reader){
        super(writer,reader);
    }
    public void deleteDB(){
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Name student that you want delete: ");
            String name = in.next();
            System.out.print("Second name student that you want delete: ");
            String secondName = in.next();
            System.out.print("Age student that you want delete: ");
            String age = in.next();
            System.out.print("Cource student that you want delete: ");
            String cource = in.next();

            writer.write("DEL "+name+" "+secondName+" "+age+" "+cource);
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
