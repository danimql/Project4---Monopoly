package monopoly;

/**
 * describes the effect type of a chance or community chest card.
 */
public enum CardEffectType {
    MOVE_ABSOLUTE,    // Move directly to a specific square (Go, Boardwalk, etc.)
    MOVE_RELATIVE,    // Move forward/backward a fixed number of spaces (Go Back 3)
    NEAREST_RAILROAD, // Move to nearest railroad ahead
    NEAREST_UTILITY,  // Move to nearest utility ahead
    GO_TO_JAIL,       // Go directly to Jail
    GET_OUT_OF_JAIL_FREE, // Keep card until used
    NO_EFFECT         // Money-only cards (ignored in this simulation)
}
