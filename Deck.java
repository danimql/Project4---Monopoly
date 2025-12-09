package monopoly;

import java.util.Random;

/**
 * represents a deck of chance or community chest cards
 * uses arrays to store the draw pile and discard pile
 */
public class Deck {

    private final Card[] cards;   //all cards in this deck (fixed)

    //drawPile and discardPile hold indices into the cards[] array.
    private final int[] drawPile;
    private final int[] discardPile;

    private int drawSize;         //how many items are currently in drawPile
    private int discardSize;      //how many items are currently in discardPile

    private final Random rng = new Random();

    /**
     * constructor
     * @param cards the full list of cards for this deck
     */
    public Deck(Card[] cards) {
        this.cards = cards;
        this.drawPile = new int[cards.length];
        this.discardPile = new int[cards.length];
        initAndShuffle();
    }

    /**
     * initializes drawPile to contain all card indices and shuffles them
     * called once at start and again whenever the deck is rebuilt
     */
    private void initAndShuffle() {
        drawSize = cards.length;
        discardSize = 0;

        //fill drawPile with indices 0..n-1
        for (int i = 0; i < cards.length; i++) {
            drawPile[i] = i;
        }

        //fisher–Yates shuffle
        for (int i = drawSize - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1); //random between 0 and i inclusive
            int temp = drawPile[i];
            drawPile[i] = drawPile[j];
            drawPile[j] = temp;
        }
    }

    /**
     * called when there are no cards left in the drawPile
     * moves all cards from discardPile back to drawPile and shuffles
     */
    private void reshuffleFromDiscard() {
        //move discardPile contents into drawPile
        for (int i = 0; i < discardSize; i++) {
            drawPile[i] = discardPile[i];
        }
        drawSize = discardSize;
        discardSize = 0;

        //shuffle the new drawPile
        for (int i = drawSize - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            int temp = drawPile[i];
            drawPile[i] = drawPile[j];
            drawPile[j] = temp;
        }
    }

    /**
     * draws the "top" card from the deck
     * if drawPile is empty, the discardPile is shuffled back in
     */
    public Card drawCard() {
        if (drawSize == 0) {
            reshuffleFromDiscard();
        }

        //take last element in drawPile as the top card
        int cardIndex = drawPile[drawSize - 1];
        drawSize--;

        return cards[cardIndex];
    }

    /**
     * returns card to the discard pile
     * DO NOT call this on 'Get Out Of Jail Free' cards until they are used
     */
    public void discardCard(Card card) {
        int cardIndex = findCardIndex(card);
        discardPile[discardSize] = cardIndex;
        discardSize++;
    }

    /**
     * used for 'Get Out Of Jail Free' cards
     * when the player finally uses the card, it is returned to the deck.
     */
    public void returnGOOJCard(Card card) {
        discardCard(card);
    }

    /**
     * looks up the index of the given card in cards[] array
     */
    private int findCardIndex(Card card) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == card) {  //reference comparison is fine here
                return i;
            }
        }
        throw new IllegalArgumentException("Card does not belong to this deck: " + card);
    }
}
