package monopoly;

public class CardEffects {

    private static final int BOARD_SIZE = 40;

    private static void discard(Card c, Deck chance, Deck cc) {
        if (c.isGOOJ()) return;    // saved until used
        if (c.isFromCommunityChest()) cc.discardCard(c);
        else chance.discardCard(c);
    }

    private static int nearestRailroad(int pos) {
        if (pos < 5) return 5;
        if (pos < 15) return 15;
        if (pos < 25) return 25;
        if (pos < 35) return 35;
        return 5;
    }

    private static int nearestUtility(int pos) {
        if (pos < 12) return 12;
        if (pos < 28) return 28;
        return 12;
    }

    /**
     * Applies the effect of a drawn card.
     */
    public static int apply(
            Card c,
            int pos,
            PlayerState p,
            Deck chance,
            Deck cc
    ) {
        switch (c.getType()) {

            case MOVE_ABSOLUTE:
                discard(c, chance, cc);
                return c.getAbsoluteIndex();

            case MOVE_RELATIVE:
                discard(c, chance, cc);
                return (pos + c.getRelativeMove() + BOARD_SIZE) % BOARD_SIZE;

            case GO_TO_JAIL:
                discard(c, chance, cc);
                p.inJail = true;
                p.failedDoubleAttempts = 0;
                return 10;

            case NEAREST_RAILROAD:
                discard(c, chance, cc);
                return nearestRailroad(pos);

            case NEAREST_UTILITY:
                discard(c, chance, cc);
                return nearestUtility(pos);

            case GET_OUT_OF_JAIL_FREE:
                p.hasGOOJ = true;
                return pos;

            case NO_EFFECT:
                discard(c, chance, cc);
                return pos;
        }
        return pos;
    }
}
