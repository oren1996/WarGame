/**
 * This class represents a player in the game.
 * Each player has his playing deck and a side deck called winning deck where he
 * saves the cards he won in each round.
 * A game deck represents another side where the player keeps the cards he drew.
 */
public class Player extends Deck {
    private String playerName;
    Deck playingDeck = new Deck(false);
    Deck winningDeck = new Deck(false);
    Deck gameDeck = new Deck(false);


    public Player(){}

    /**
     * Constructor which initialize the playing deck and the winning deck.
     * Both are empty at the beginning.
     * @param playerName is the player's name.
     */
    public Player(String playerName){
        this.playerName = playerName;
        this.playingDeck = new Deck(false);
        this.winningDeck = new Deck(false);
    }

    /* Returns the player's name. */
    public String getPlayerName() { return playerName; }

    /* Sets the player's name got as an input by the user. */
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    /**
     * Adds a card to the player's playing deck.
     * @param card: a Card type variable represents the card that should be added.
     */
    public void addCardToPlayingDeck(Card card){
        int sizeDeck = this.playingDeck.deck.size();
        this.playingDeck.deck.add(sizeDeck, card);
    }

    /**
     * Adds a card to the player's winning deck.
     * @param card: a Card type variable represents the card that should be added.
     */
    public void addCardToWinningDeck(Card card) {
        int sizeDeck = this.winningDeck.deck.size();
        this.winningDeck.deck.add(sizeDeck, card);
    }

    /* When the player draws a card it's being removed from his playing deck.
     * And added to his side game deck */
    public void drawCard(){
        Card firstCard = this.playingDeck.removeTopCard();
        this.gameDeck.addCard(firstCard);
    }

    /* Returns true if a deck is empty. */
    public boolean outOfCards(){
        return this.playingDeck.isEmpty();
    }

    @Override
    public String toString(){
        return this.playerName;
    }
}
