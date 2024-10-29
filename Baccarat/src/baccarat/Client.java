package baccarat;

import java.net.Socket;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        // if (args.length != 1){
        //     System.out.println("usage: java -cp classes baccarat/Client <host:port>");
        //     return;
        // }
        // String arg = args[0];
        // String host = arg.split(":")[0];
        // int port = Integer.parseInt(arg.split(":")[1]);

        try {
            Socket clientConn = new Socket("localhost", 12345);
            Console cons = System.console();
            String command = "";
            while (true){
                System.out.println("Login <name> <wealth>");
                System.out.println("Bet <amount to bet>");
                System.out.println("Deal P or Deal B");
                command = cons.readLine("Please enter commands in the above order: \n");
        
                OutputStream os = clientConn.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
        
                dos.writeUTF(command);
                if (command.equals("end")){
                    break;
                }

            }
    
            InputStream is = clientConn.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            String result = dis.readUTF();
            
            if (result.equals("Insufficient balance!")){
                System.out.println(result);
            } else {
                String player = result.split(",")[0];
                String banker = result.split(",")[1];

                int playerHand = 0;
                int bankerHand = 0;
                for (int i = 1 ; i < player.split("\\|").length; i++){
                    playerHand += Integer.parseInt(player.split("\\|")[i]);
                    bankerHand += Integer.parseInt(banker.split("\\|")[i]);
                }
    
                if (bankerHand > playerHand){
                    System.out.printf("Banker wins with %d points.\n", bankerHand - playerHand);
                } else if (playerHand > bankerHand){
                    System.out.printf("Player wins with %d points.\n", playerHand - bankerHand);
                } else if (playerHand == bankerHand){
                    System.out.println("It's a draw!");
                }
            }

            //System.out.println(result);
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
