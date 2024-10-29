package baccarat;

import java.io.*;
import java.net.Socket;

public class BaccaratEngine {
    Writer w = new Writer();
    Deck deck;
    Party player = new Party("p");
    Party banker = new Party("b");
    String personBetOn;
    int betAmount;
    Socket clientConn;
    
    
    public BaccaratEngine(Deck deck, Socket clientConn) {
        this.deck = deck;
        this.clientConn = clientConn;
    }

    public void runLogic(){
        getCommand();
        if (!isContinue() && isWin()){
            w.writeToAccount(player.getAccount(), String.valueOf(player.getWealth()));      

        } else {
            dealXtraCard();
            if (isWin()){
                w.writeToAccount(player.getAccount(), String.valueOf(player.getWealth()));  
            }
        }
        sendResult("");
    }


    public void getCommand(){
        String command = "";
        try {
            InputStream is = clientConn.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            while ((command = dis.readUTF()) != null){
                System.out.println("Command received: " + command);
                String[] commandArr = command.split(" ");
                String action = commandArr[0].toLowerCase();
                switch (action) {
                    case "login":
                        login(commandArr[1], commandArr[2]);
                        player.setWealth(Integer.parseInt(commandArr[2]));    
    
                        break;
    
                    case "bet":
                        this.betAmount = Integer.parseInt(commandArr[1]);
                    
                        break;
    
                    case "deal":
                        this.personBetOn = commandArr[1];
                        dealDeck();
                        break;

                    case "end":
                        return;
                        //break;
                    default:
                        break;
                }

            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error in reading command!");
            e.printStackTrace();
        }
        
    }

    
    public void login(String name, String wealth) throws IOException{
        File account = new File("C:\\Users\\Clare Lau\\vttp5_sdf_pastAssessments\\Baccarat\\data\\" + name + ".db");
        if (!account.exists()){
            account.createNewFile();
        }
        player.setAccount(account);
        w.writeToAccount(account, wealth);
    
    }

    public void dealDeck(){
        player.addToHand(deck.dealCard(), deck.dealCard());
        banker.addToHand(deck.dealCard(), deck.dealCard());
        w.writeToCardsDB(deck);
    }

    public boolean isContinue(){
        if (player.getHandValue() >= 15 && banker.getHandValue() >= 15){
            return false;
        }
        return true;
    }

    public boolean isWin(){
        if (player.getHandValue() > banker.getHandValue()){
            if (personBetOn.equalsIgnoreCase("p")){
                player.setWealth(player.getWealth() + betAmount);

            } else {
                if (betAmount > player.getWealth()){
                    sendResult("Insufficient balance!");
                } else player.setWealth(player.getWealth() - betAmount);  
            } 
            return true;

        } else if (player.getHandValue() < banker.getHandValue()){
            if (personBetOn.equalsIgnoreCase("b")){
                player.setWealth(player.getWealth() + betAmount);

            } else {
                if (betAmount > player.getWealth()){
                    sendResult("Insufficient balance!");
                } else player.setWealth(player.getWealth() - betAmount);
            }
            return true;

        } else if (player.getHandValue() == banker.getHandValue()){
            return true;
        }
        return false;
    }

    public void sendResult(String result){
        try {
            OutputStream os = clientConn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            
            if (result.equals("Insufficient balance!")){

            } else result = player.getHand() + "," + banker.getHand();
            System.out.println(result);

            dos.writeUTF(result);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error sending result to client");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void dealXtraCard(){
        player.addXtraCard(deck.dealCard());
        banker.addXtraCard(deck.dealCard());
        w.writeToCardsDB(deck);
    }
}
