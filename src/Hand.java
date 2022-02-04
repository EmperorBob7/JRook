import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/** Represents a collection of cards in the possession of a player
 * @author Zhongyou Wu
 * @author Cromlechs#5019
 */
public class Hand {
    public HashMap<Card.Suit, ArrayList<Card>> hand;
    public JPanel panel;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    final int screenWidth = size.width;
    final int screenHeight = size.height;
    final int height = screenHeight/5;
    final int width = screenWidth*5/6;
    final double ratio = 0.722;
    final int cardHeight = height * 3 / 4;
    final int cardWidth = (int) (cardHeight * ratio);
    /**
     * Class constructor that initiates the hand.
     */
    public Hand() {
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, -cardHeight/2, (height - cardHeight)/2));
        panel.setPreferredSize(new Dimension(width, height));
        panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panel.setBackground(new Color(210,210,210));

        hand = new HashMap<>();
        for(Card.Suit suit : Card.Suit.values()) {
            this.hand.put(suit, new ArrayList<Card>());
        }
    }

    /**
     * Class constructor that accept an arraylist argument
     * @param hand
     */
    public Hand(ArrayList<Card> hand)
    {
        this();
        for(Card card : hand) {
            addCard(card);
        }
        
    }

    /**
     * Class constructor that accept an array argument
     * @param hand
     */
    public Hand(Card[] hand) {
        this(new ArrayList<Card>(Arrays.asList(hand)));
    }

    public void addCard(Card card) {
        hand.get(card.suit).add(card);
        panel.add(card.getImageButton((int)(cardHeight * ratio),cardHeight));
        Game.game.repaint();
    }

    public void removeCard(Card card) {
        hand.get(card.suit).remove(card);
        panel.remove(card.button);
        Game.game.repaint();
    }

    /**
     * @return The hand as a map of arraylist. With each arraylist being all the card of the suit, and the suit as the key.
     */
    public HashMap<Card.Suit, ArrayList<Card>> getHand() {
        return hand;
    }

    /**
     * @param suit - the desired suit
     * @return All the cards of the given suit in hand.
     */
    public ArrayList<Card> getSuit(Card.Suit suit) {
        return hand.get(suit);
    }

    public int getValue() {
        int value = 0;
        for(ArrayList<Card> suit : hand.values()) {
            for (Card card : suit) {
                switch (card.value) {
                    case 1:
                        value += 15;
                        break;
                    case 5:
                        value += 5;
                        break;
                    case 10:
                    case 14:
                        value += 10;
                        break;
                    case 20:
                        value += 20;
                        break;
                }
            }
        }
        return value;
    }

    public short getSize() {
        short size = 0;
        for(ArrayList<Card> suit : hand.values()) {
            size += suit.size();
        }
        return size;
    }

    public JPanel getPanel() {
        return panel;
    }

    public String toString() {
        String result = "";
        for (ArrayList<Card> suit : hand.values()) {
            for (Card card : suit) {
                result += card + ", ";
            }
        }
        return result;
    }
}

