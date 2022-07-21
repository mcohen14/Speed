
import java.util.ArrayList;

public class Deck {
    ArrayList<Card> deck = new ArrayList<Card>();

    public Deck(){
        for(int i = 1; i<=13; i++){
            deck.add(new Card(i, "♣"));
        }
        for(int i = 14; i<=26; i++){
            deck.add(new Card(i-13, "♥"));
        }
        for(int i = 27; i<=39; i++){
            deck.add(new Card(i-26, "♠"));
        }
        for(int i = 40; i<=52; i++){
            deck.add(new Card(i-39, "♦"));
        }
    }

    public void print(){
        for(int i = 0; i<=51; i++){
            System.out.println(deck.get(i).getText());
        }
    }

    public Card remove(int x){
        return deck.remove(x);
    }

    public static void main(String[] args){
        Deck d = new Deck();
        d.print();
    }
}