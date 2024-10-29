package baccarat;

import java.io.*;

public class Party {
    private int handValue;
    private String hand;
    private String marker;
    private int wealth;
    private File account;

    

    public Party(String marker) {
        this.marker = marker;
    }

    public void setWealth(int wealth) {
        this.wealth = wealth;
    }

    public int getWealth() {
        return wealth;
    }

    public void setAccount(File account) {
        this.account = account;
    }

    public File getAccount() {
        return account;
    }

    public void addToHand(String cardDealt1, String cardDealt2){
      
        String card1 = cardDealt1.split("\\.")[0];
        String card2 = cardDealt2.split("\\.")[0];
        int card1Value = Integer.parseInt(card1);
        int card2Value = Integer.parseInt(card2);

        if (card1Value > 10 && card2Value > 10){
            card1Value = 10;
            card2Value = 10;
            card1 = "10";
            card2 = "10";
        } else if (card1Value > 10){
            card1Value = 10;
            card1 = "10";
        } else if (card2Value > 10){
            card2Value = 10;
            card2 = "10";
        }

        handValue = card1Value + card2Value;
        this.hand = String.format("%s|%s|%s", marker, card1, card2); 
    }

    public int getHandValue(){
        return this.handValue;
    }

    public String getHand() {
        return hand;
    }

    public void addXtraCard(String cardDealt3){
        
        String card3 = cardDealt3.split("\\.")[0];
        int card3Value = Integer.parseInt(card3);
        if (card3Value > 10){
            card3Value = 10;
            card3 = "10";
        }
        handValue += card3Value;
        this.hand = String.format("%s|%s", this.hand, card3);
    }
}
