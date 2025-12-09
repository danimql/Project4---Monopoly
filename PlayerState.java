package monopoly;

/**
 * Holds variable player state used by card logic and jail logic.
 */
public class PlayerState {
    public boolean inJail = false;
    public boolean hasGOOJ = false;
    public int doublesCounter = 0;
    public int failedDoubleAttempts = 0;
}
