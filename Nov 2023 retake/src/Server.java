
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        // int port = Integer.parseInt(args[0]);
        // String staticPath = args[1];

        // if (args.length < 2){
        //     System.out.println("Please input the correct command with the follwing arguments!");
        //     System.out.println("Usage: java -cp classes Server <port> <docroot>");
        //     return;
        // }

        int port = 3000;
        String staticPath = "C:\\Users\\Clare Lau\\vttp5_sdf_pastAssessments\\Nov 2023 retake\\vttp2023-batch4-sdf-nov24-assessment\\task02\\static";

        try {
            ServerSocket server = new ServerSocket(port);
            Socket clientConn = server.accept();
    
            //Getting inputstream
            InputStream is = clientConn.getInputStream();
            DataInputStream dis = new DataInputStream(is);
    
            //Reading the 'GET' command from the client
            String get = dis.readUTF();
            String[] getArr = get.split(" ");
    
            File fileRequested = new File(staticPath + getArr[1]);
    
            HttpWriter writer = new HttpWriter(clientConn.getOutputStream());
    
            //Inputstream for reading the html file requested
            InputStream fis = new FileInputStream(fileRequested);
            BufferedInputStream bis = new BufferedInputStream(fis);
    
            //Creating a buffer to read html into
            byte[] buff = new byte[1024 * 8];
            
            String response = "";
            if (fileRequested.exists()){
                response = getArr[2] + " 200 OK";
                writer.writeString(response);
                writer.writeString("Content-Type: text/html");
                writer.writeString();
    
                while (bis.read(buff) != -1){
                    writer.writeBytes(buff);
                }
                
            } else {
                response = getArr[2] + " 404 Not Found";
                writer.writeString("Content-Type: text/html");
                writer.writeString();
                writer.writeString("Resource " + getArr[1] + " not found");
            }
            writer.flush();
            bis.close();
            fis.close();
            writer.close();
            server.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
