import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
       try(Socket socket = new Socket("127.0.0.1",8000);
           BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
           BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())))
       {
           Scanner in = new Scanner(System.in);
           System.out.print("Your key: ");
           String request = in.next();
           writer.write(request);
           writer.newLine();
           writer.flush();


           String respons = reader.readLine(),a="";
           System.out.println(respons);
           String[] str = respons.split(" ");
           switch (str[0]){
               case ("USER"):
                   User user = new User(writer,reader);
                   user.readDB();
                   break;
               case ("MENEGER"):
                   Meneger meneger = new Meneger(writer,reader);
                   while(true){
                   System.out.println("1 - show,2 - exit");
                   a = in.next();
                   if(a.equals("1")){meneger.readDB();}else if(a.equals("2")){break;}
                   }
                   break;
               case ("DIRECTOR"):
                   Director director = new Director(writer,reader);
                   while(true) {
                       System.out.println("1 - show,2 - add, 3 - exit");
                       a = in.next();
                       if (a.equals("1")) {
                           director.readDB();
                       } else if (a.equals("2")) {
                           director.writeDB();
                       } else {
                           break;
                       }
                   }
                   break;
           }

       }
       catch (IOException e){
           e.printStackTrace();
       }
    }
}
