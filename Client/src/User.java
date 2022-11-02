import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class User {

    BufferedWriter writer;
    BufferedReader reader;
    public User(BufferedWriter writer,BufferedReader reader){
        this.writer=writer;
        this.reader=reader;
    }

    public void readDB(){
        try{
            writer.write("SHOW null null null null");
            writer.newLine();
            writer.flush();

            String response,xmlFile="";
            boolean flag = true;
            while (flag == true){
                response = reader.readLine();
                if(response.equals("break")){
                    flag = false;
                }else{
                    System.out.println(response);
                }
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
