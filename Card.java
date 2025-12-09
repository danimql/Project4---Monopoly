package monopoly;

/**
 * Represents a single Chance or Community Chest card.
 * Stores only movement-related effects.
 */
public class Card {

    private final String description;
    private final CardEffectType type;
    private final int absoluteIndex;       // Used for MOVE_ABSOLUTE
    private final int relativeMove;        // Used for MOVE_RELATIVE
    private final boolean fromCC;          // true = Community Chest, false = Chance

    // MOVE_ABSOLUTE constructor
    public Card(String desc, CardEffectType type, int targetIndex, boolean isCC) {
        this.description = desc;
        this.type = type;
        this.absoluteIndex = targetIndex;
        this.relativeMove = 0;
        this.fromCC = isCC;
    }

    // MOVE_RELATIVE constructor
    public Card(String desc, CardEffectType type, int relativeMove) {
        this.description = desc;
        this.type = type;
        this.relativeMove = relativeMove;
        this.absoluteIndex = -1;
        this.fromCC = false;
    }

    // Cards with no movement index
    public Card(String desc, CardEffectType type, boolean isCC) {
        this.description = desc;
        this.type = type;
        this.fromCC = isCC;
        this.absoluteIndex = -1;
        this.relativeMove = 0;
    }

    public String getDescription() { return description; }
    public CardEffectType getType() { return type; }
    public int getAbsoluteIndex() { return absoluteIndex; }
    public int getRelativeMove() { return relativeMove; }
    public boolean isFromCommunityChest() { return fromCC; }
    public boolean isGOOJ() { return type == CardEffectType.GET_OUT_OF_JAIL_FREE; }

    @Override
    public String toString() {
        return description + " [" + type + "]";
    }
}
