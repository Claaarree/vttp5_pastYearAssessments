package baccarat;

import java.net.*;

public class Server {
    public static void main(String[] args) {
        // int port;
        // int numDecks;

        // if (args.length != 2){
        //     System.out.println("usage: java -cp classes baccarat/Main <port> <number of decks>");
        //     return;
        // } else {
        //     try {
        //         port = Integer.parseInt(args[0]);
        //         numDecks = Integer.parseInt(args[1]);
                
        //     } catch (Exception e) {
        //         // TODO: handle exception
        //         System.out.println("Please input valid port number and number of decks desired.");
        //         return;
        //     }
        // }

        Writer w = new Writer();
       
        Deck deck = new Deck(4);
        deck.shuffleDeck();
        w.writeToCardsDB(deck);
        
        try {
            ServerSocket server = new ServerSocket(12345);
            System.out.println("Waitng for client connection...");
            Socket clientConn = server.accept();
            System.out.println("Got a connection!");
            
            BaccaratEngine be = new BaccaratEngine(deck, clientConn);
            be.runLogic();
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } 
        
        


    }
}
