package monopoly;

/**
 * represents a single chance or community chest card.
 * only stores information needed for movement + jail rules.
 */
public class Card {

    private final String name;          // Human-readable name
    private final CardEffectType type;  // What this card does
    private final int targetIndex;      // Board index for MOVE_ABSOLUTE
    private final int relativeOffset;   // Steps for MOVE_RELATIVE (e.g., -3)
    private final boolean communityChest; // true = Community Chest, false = Chance

    /**
     * constructor for MOVE_ABSOLUTE cards.
     * ex: "Advance to Go", "Advance to Boardwalk"
     */
    public Card(String name, CardEffectType type, int targetIndex, boolean isCommunityChest) {
        this.name = name;
        this.type = type;
        this.targetIndex = targetIndex;
        this.relativeOffset = 0;
        this.communityChest = isCommunityChest;
    }

    /**
     * constructor for MOVE_RELATIVE cards.
     * ex: "Go Back 3 Spaces"
     */
    public Card(String name, CardEffectType type, int relativeOffset) {
        this.name = name;
        this.type = type;
        this.targetIndex = -1;
        this.relativeOffset = relativeOffset;
        this.communityChest = false; // not important here
    }

    /**
     * constructor for cards w/no numeric position. (railroad, utility)
     */
    public Card(String name, CardEffectType type, boolean isCommunityChest) {
        this.name = name;
        this.type = type;
        this.targetIndex = -1;
        this.relativeOffset = 0;
        this.communityChest = isCommunityChest;
    }

    //getters
    public String getName() { return name; }
    public CardEffectType getType() { return type; }
    public int getTargetIndex() { return targetIndex; }
    public int getRelativeOffset() { return relativeOffset; }
    public boolean isCommunityChest() { return communityChest; }

    @Override
    public String toString() {
        return name + " [" + type + "]";
    }
}
