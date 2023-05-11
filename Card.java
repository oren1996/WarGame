/**
 * A class represents a card in the game.
 * Every card has a number and a shape.
 */
public class Card {

    private int number;
    private Shape shape;

    public Card() {
    }

    /**
     * Constructor for the card. initialize it's number and shape.
     * @param number The number that appears on the card
     * @param shape The Shape that appears on the card
     */
    public Card(int number, Shape shape){
        this.number = number;
        this.shape = shape;
    }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public Shape getShape() { return shape; }
    public void setShape(Shape shape) { this.shape = shape; }

    /**
     * Compare the numbers of two cards.
     * @param Other stands for the other card being compared to.
     * @return 1 if first card is higher, -1 if the second is higher and 0 if both the same.
     */
    public int compare(Card Other){
        return Integer.compare(this.number, Other.number);
    }

    /**
     * Adjusting the shapes to their codes.
     * @return Card's number and shape.
     */
    @Override
    public String toString() {
        String form = null;
        if (this.shape.equals(Shape.Spades)) {
            form = "\u2660";
        }
        if (this.shape.equals(Shape.Diamonds)) {
            form = "\u2666";
        }
        if (this.shape.equals(Shape.Clubs)) {
            form = "\u2663";
        }
        if (this.shape.equals(Shape.Hearts)) {
            form = "\u2665";
        }

        switch (this.shape) {
            case Hearts:
            case Clubs:
            case Diamonds:
            case Spades:
                switch (this.number) {
                    case 1:
                        return "Ace of " + form;
                    case 11:
                        return "Jack of " + form;
                    case 12:
                        return "Queen of " + form;
                    case 13:
                        return "King of " + form;
                    default:
                        return this.number + " of " + form;
                }
        }
        return "";
    }

}