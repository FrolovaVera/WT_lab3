import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Cerver {
    public static void connectedToServer(BufferedWriter writer,String access){
        try{
            writer.write(access);
            writer.newLine();
            writer.flush();
        }catch (IOException e){throw new RuntimeException(e);}
    }

    public static void controlingRequest(BufferedWriter writer,BufferedReader reader){
        try {
            String request = reader.readLine();
            String[] atribut = request.split(" ");
            String[] mas = new String[5];
            mas=atribut;
            Database database = new Database(mas[1],mas[2],mas[3],mas[4]);

            switch (atribut[0]){
                case ("ADD"):
                    database.addNewDocument(mas[1],mas[2],mas[3],mas[4]);
                    writer.write("New student is add");
                    writer.newLine();
                    writer.flush();
                    break;
                case ("SHOW"):
                    writer.write("You read file");
                    writer.newLine();
                    writer.flush();
                    database.showDocument(writer);

            }
        }
        catch (IOException e){throw new RuntimeException(e);}
    }

    public static void main(String[] args) {
        try(ServerSocket server =new ServerSocket(8000))
        {
            System.out.println("Server started");
            while (true){
                try(Socket socket = server.accept();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));){

                    String request = reader.readLine();
                    switch (request){
                        case ("USER"):
                            connectedToServer(writer,"USER connected to server");
                            break;
                        case ("MENEGER"):
                            connectedToServer(writer,"MENEGER connected to server");
                            break;
                        case ("DIRECTOR"):
                            connectedToServer(writer,"DIRECTOR connected to server");
                            break;
                    }

                     while(true){
                        controlingRequest(writer,reader);
                    }


                }
                catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
        }
        catch (IOException e)
        { throw new RuntimeException(e);}
    }

}