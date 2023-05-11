import org.w3c.dom.ranges.Range;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class represents a deck of cards.
 * Each deck consists of an ArrayList of Card type.
 */
public class Deck extends Card {
    public ArrayList<Card> deck = new ArrayList<>();
    Random rnd1 = new Random(42);

    public static void main(String[] args) {
        Deck deck = new Deck(true);
        deck.shuffle();
        for (Card card: deck.deck) {
            System.out.println(card.toString());
        }
    }
    /* Initialize a an empty deck. */
    public Deck() {
        this(false);
    }

    /* Initialize the deck with 52 cards, only if we got "true" as parm. */
    public Deck(boolean bool) {
        if (bool) {
            for (int i = 1; i <= 13; i++) {
                Card card = new Card(i, Shape.Spades);
                deck.add(card);
            }
            for (int i = 1; i <= 13; i++) {
                Card card = new Card(i, Shape.Diamonds);
                deck.add(card);
            }
            for (int i = 1; i <= 13; i++) {
                Card card = new Card(i, Shape.Clubs);
                deck.add(card);
            }
            for (int i = 1; i <= 13; i++) {
                Card card = new Card(i, Shape.Hearts);
                deck.add(card);
            }
        }
    }


    /* Adds card at the start of the deck */
    public void addCard(Card card) {
        this.deck.add(0, card);
    }

    /* Removes card from the end of the deck */
    public Card removeTopCard() {
        int sizeDeck = this.deck.size() - 1;
        Card card = this.deck.get(sizeDeck);
        this.deck.remove(sizeDeck);
        return card;
    }

    /* Returns true if the deck is empty and false otherwise. */
    public boolean isEmpty() {
        return this.deck.size() == 0;
    }

    /* Shuffles the deck by randomly grilled numbers
     which we use to switch the cards indexes in the list. */
    public void shuffle() {
        Card firstCard;
        Card secondCard;
        for (int i = 0; i < 50; i++) {
            int index1 = Main.rnd.nextInt(this.deck.size());
            int index2 = Main.rnd.nextInt(this.deck.size());
            firstCard = this.deck.get(index1);
            secondCard = this.deck.get(index2);
            this.deck.set(index1, secondCard);
            this.deck.set(index2, firstCard);
        }
    }
}