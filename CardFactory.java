package monopoly;

/**
 * utility class that constructs the chance and community chest decks
 * uses classic USA monopoly board indexes:
 *
 *  0  Go
 *  1  Mediterranean Ave
 *  2  Community Chest
 *  3  Baltic Ave
 *  4  Income Tax
 *  5  Reading Railroad
 *  6  Oriental Ave
 *  7  Chance
 *  8  Vermont Ave
 *  9  Connecticut Ave
 * 10  Jail / Just Visiting
 * 11  St. Charles Place
 * 12  Electric Company
 * 13  States Ave
 * 14  Virginia Ave
 * 15  Pennsylvania Railroad
 * 16  St. James Place
 * 17  Community Chest
 * 18  Tennessee Ave
 * 19  New York Ave
 * 20  Free Parking
 * 21  Kentucky Ave
 * 22  Chance
 * 23  Indiana Ave
 * 24  Illinois Ave
 * 25  B&O Railroad
 * 26  Atlantic Ave
 * 27  Ventnor Ave
 * 28  Water Works
 * 29  Marvin Gardens
 * 30  Go to Jail
 * 31  Pacific Ave
 * 32  North Carolina Ave
 * 33  Community Chest
 * 34  Pennsylvania Ave
 * 35  Short Line RR
 * 36  Chance
 * 37  Park Place
 * 38  Luxury Tax
 * 39  Boardwalk
 */
public class CardFactory {

    public static Deck createChanceDeck() {
        Card[] chanceCards = new Card[] {
            new Card("Advance to Boardwalk", CardEffectType.MOVE_ABSOLUTE, 39, false),
            new Card("Advance to Go", CardEffectType.MOVE_ABSOLUTE, 0, false),
            new Card("Advance to Illinois Avenue", CardEffectType.MOVE_ABSOLUTE, 24, false),
            new Card("Advance to St. Charles Place", CardEffectType.MOVE_ABSOLUTE, 11, false),

            new Card("Advance to nearest Railroad 1", CardEffectType.NEAREST_RAILROAD, false),
            new Card("Advance to nearest Railroad 2", CardEffectType.NEAREST_RAILROAD, false),

            new Card("Advance token to nearest Utility", CardEffectType.NEAREST_UTILITY, false),

            new Card("Bank pays you dividend of $50", CardEffectType.NO_EFFECT, false),

            new Card("Get Out of Jail Free", CardEffectType.GET_OUT_OF_JAIL_FREE, false),

            new Card("Go Back 3 Spaces", CardEffectType.MOVE_RELATIVE, -3),

            new Card("Go to Jail", CardEffectType.GO_TO_JAIL, false),

            new Card("Make general repairs on all your property", CardEffectType.NO_EFFECT, false),
            new Card("Speeding fine $15", CardEffectType.NO_EFFECT, false),

            new Card("Take a trip to Reading Railroad", CardEffectType.MOVE_ABSOLUTE, 5, false),

            new Card("You have been elected Chairman of the Board", CardEffectType.NO_EFFECT, false),
            new Card("Your building loan matures", CardEffectType.NO_EFFECT, false)
        };

        return new Deck(chanceCards);
    }

    public static Deck createCommunityChestDeck() {
        Card[] ccCards = new Card[] {
            new Card("Advance to Go", CardEffectType.MOVE_ABSOLUTE, 0, true),
            new Card("Bank error in your favor. Collect $200", CardEffectType.NO_EFFECT, true),
            new Card("Doctor’s fee. Pay $50", CardEffectType.NO_EFFECT, true),
            new Card("From sale of stock you get $50", CardEffectType.NO_EFFECT, true),

            new Card("Get Out of Jail Free", CardEffectType.GET_OUT_OF_JAIL_FREE, true),

            new Card("Go to Jail", CardEffectType.GO_TO_JAIL, true),

            new Card("Holiday fund matures. Receive $100", CardEffectType.NO_EFFECT, true),
            new Card("Income tax refund. Collect $20", CardEffectType.NO_EFFECT, true),
            new Card("It is your birthday. Collect $10 from every player", CardEffectType.NO_EFFECT, true),
            new Card("Life insurance matures. Collect $100", CardEffectType.NO_EFFECT, true),
            new Card("Pay hospital fees of $100", CardEffectType.NO_EFFECT, true),
            new Card("Pay school fees of $50", CardEffectType.NO_EFFECT, true),
            new Card("Receive $25 consultancy fee", CardEffectType.NO_EFFECT, true),
            new Card("You are assessed for street repair", CardEffectType.NO_EFFECT, true),
            new Card("You have won second prize in a beauty contest", CardEffectType.NO_EFFECT, true),
            new Card("You inherit $100", CardEffectType.NO_EFFECT, true)
        };

        return new Deck(ccCards);
    }

    private CardFactory() {
        //DO NOT INSTANTIATE - utility class
    }
}
