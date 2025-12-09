package monopoly;

/**
 * Types of effects Chance and Community Chest cards may have.
 * We only implement effects that move the token or jail behavior.
 */
public enum CardEffectType {
    MOVE_ABSOLUTE,
    MOVE_RELATIVE,
    GO_TO_JAIL,
    NEAREST_RAILROAD,
    NEAREST_UTILITY,
    GET_OUT_OF_JAIL_FREE,
    NO_EFFECT
}
