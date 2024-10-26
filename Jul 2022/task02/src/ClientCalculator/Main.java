package ClientCalculator;

import java.net.Socket;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        int port = 80;
        String serverName = "sdf.chuklee.com";
        //String serverName = "localhost";

        try {
            Socket clientConn = new Socket(serverName, port);

            OutputStream os = clientConn.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            InputStream is = clientConn.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
    
            //getting information from server
            String information = ois.readUTF();
            String requestID = information.split(" ")[0];
            String numbers = information.split(" ")[1];
            String[] numbersArr = numbers.split(",");

            //calculating average
            int sum = 0;
            for (int i = 0; i < numbersArr.length; i++){
                int number = Integer.parseInt(numbersArr[i]);
                sum += number;
            }
            float average = sum /(float)numbersArr.length;

            //getting input from user 
            Console cons = System.console();
            String NRICname = cons.readLine("Please input your name as per NRIC:\n");
            String email = cons.readLine("Please enter your email:\n");

            //sending output to server
            oos.writeUTF(requestID);
            oos.writeUTF(NRICname);
            oos.writeUTF(email);
            oos.writeFloat(average);

            //reading boolean from server
            boolean result = ois.readBoolean();
            if (result == false){
                System.out.println("FAILED");
                System.out.println(ois.readUTF());
            }
            clientConn.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
