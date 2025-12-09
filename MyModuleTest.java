package monopoly;

public class MyModuleTest {

    public static void main(String[] args) throws Exception {

        System.out.println("=== TEST: Creating Decks ===");
        Deck chance = CardFactory.createChanceDeck();
        Deck cc     = CardFactory.createCommunityChestDeck();
        System.out.println("Chance deck and Community Chest deck created successfully.\n");

        System.out.println("=== TEST: Drawing Cards ===");
        for (int i = 1; i <= 5; i++) {
            Card c = chance.drawCard();
            System.out.println("Draw " + i + ": " + c.getName() + " [" + c.getType() + "]");
            if (c.getType() != CardEffectType.GET_OUT_OF_JAIL_FREE) {
                chance.discardCard(c);
            }
        }

        System.out.println("\n=== TEST: Drawing ALL Cards to Force Reshuffle ===");
        for (int i = 1; i <= 20; i++) {  //chance has 16 cards, this will force reshuffle
            Card c = chance.drawCard();
            System.out.println("Draw " + i + ": " + c.getName());
            if (c.getType() != CardEffectType.GET_OUT_OF_JAIL_FREE) {
                chance.discardCard(c);
            }
        }

        System.out.println("\n=== TEST: JSON Logger ===");
        MoveLogger logger = new MoveLogger();

        int pos = 0;            //fake initial pos
        int turn = 1;

        for (int i = 0; i < 10; i++) {
            int from = pos;
            pos = (pos + 3) % 40;   //fake movement of +3 spaces each turn
            logger.logMove(turn++, "A", from, pos, 1, 2, "TEST_MOVE");
        }

        logger.writeToFile("test_output.json");
        System.out.println("test_output.json created successfully.");

        System.out.println("\n=== ALL YOUR COMPONENTS WORK ON THEIR OWN ===");
    }
}
