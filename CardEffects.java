package monopoly;

/**
 * helper methods for applying chance/community chest card effects
 * to the player's board position.
 */
public class CardEffects {

    //board indices for special squares
    public static final int JAIL_INDEX = 10;
    private static final int BOARD_SIZE = 40;

    //railroads and utilities on the classic board
    private static final int[] RAILROADS = {5, 15, 25, 35};
    private static final int[] UTILITIES = {12, 28};

    /**
     * Returns the index of the nearest target square strictly ahead
     * of currentPos, wrapping around the board if needed.
     */
    private static int nearestForward(int currentPos, int[] targets) {
        int bestTarget = targets[0];
        int minDistance = BOARD_SIZE + 1;

        for (int t : targets) {
            int distance = (t - currentPos + BOARD_SIZE) % BOARD_SIZE;
            if (distance == 0) {
                distance = BOARD_SIZE;
            }
            if (distance < minDistance) {
                minDistance = distance;
                bestTarget = t;
            }
        }
        return bestTarget;
    }

    //nearest railroad ahead. 
    public static int nearestRailroad(int currentPos) {
        return nearestForward(currentPos, RAILROADS);
    }

    //nearest utility ahead. 
    public static int nearestUtility(int currentPos) {
        return nearestForward(currentPos, UTILITIES);
    }

    /**
     * Applies a card's positional effect to the player's current position
     * and returns the new board index.
     */
    public static int applyCardEffect(Card card, int currentPos) {
        switch (card.getType()) {
            case MOVE_ABSOLUTE:
                return card.getTargetIndex();

            case MOVE_RELATIVE:
                int newPos = (currentPos + card.getRelativeOffset()) % BOARD_SIZE;
                if (newPos < 0) {
                    newPos += BOARD_SIZE;
                }
                return newPos;

            case NEAREST_RAILROAD:
                return nearestRailroad(currentPos);

            case NEAREST_UTILITY:
                return nearestUtility(currentPos);

            case GO_TO_JAIL:
                return JAIL_INDEX;

            case NO_EFFECT:
            case GET_OUT_OF_JAIL_FREE:
            default:
                //position doesn't change
                return currentPos;
        }
    }
}
