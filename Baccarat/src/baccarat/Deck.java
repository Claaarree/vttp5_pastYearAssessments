package baccarat;

import java.util.*;


public class Deck {
    private String[] suits = {"1", "2", "3", "4"};
    private String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
    private List<String> completeDeck = new ArrayList<>();
    
    public Deck(int numDecks) {
        for (int i = 0; i < numDecks; i++){
            buildDeck();
        }
    }

    public List<String> getCompleteDeck() {
        return this.completeDeck;
    }

    public void buildDeck(){
        for (String v : values){
            for (int i = 0; i < suits.length; i++){
                String card = v + "." + suits[i];
                this.completeDeck.add(card);
            }
        }
    }

    public void shuffleDeck(){
        Collections.shuffle(this.completeDeck);
    }

    public String dealCard(){
        //System.out.println("in dealCard");
        String cardDealt = this.completeDeck.removeFirst();
        //System.out.println(cardDealt);
        return cardDealt;
    }
}
