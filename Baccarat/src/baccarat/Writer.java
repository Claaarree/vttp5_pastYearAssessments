package baccarat;

import java.io.*;

public class Writer {
    private File cards = new File("C:\\Users\\Clare Lau\\vttp5_sdf_pastAssessments\\Baccarat\\data\\cards.db");
    

    public void writeToCardsDB(Deck deck){
        try {
            FileWriter fw = new FileWriter(cards, false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String card : deck.getCompleteDeck()){
                bw.write(card + "\n");

            }
            bw.flush();
            fw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void writeToAccount(File account, String wealth){
        try {
            FileWriter fw = new FileWriter(account);
            fw.write(wealth);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error in writing to account!");
            e.printStackTrace();
        }
    }
}
