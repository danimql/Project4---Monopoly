package monopoly;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * logs each movement of the player and writes it as a JSON array (separate file, refresh the project for updates after each run)
 * tracks previous player moves for data tracking
 */
public class MoveLogger {

    /**
     * data holder for one move
     */
    public static class MoveEntry {
        int turn;        //turn number (1..n)
        String strategy; //"A" or "B"
        int fromPos;     //starting board index
        int toPos;       //ending board index
        int dice1;       //first die 
        int dice2;       //second die
        String reason;   //"ROLL", "CHANCE", "COMMUNITY_CHEST", "GO_TO_JAIL", etc.

        public MoveEntry(int turn, String strategy, int fromPos, int toPos,
                         int dice1, int dice2, String reason) {
            this.turn = turn;
            this.strategy = strategy;
            this.fromPos = fromPos;
            this.toPos = toPos;
            this.dice1 = dice1;
            this.dice2 = dice2;
            this.reason = reason;
        }
    }

    private final List<MoveEntry> moves = new ArrayList<>();

    /**
     * call once for every movement of player
     */
    public void logMove(int turn, String strategy, int fromPos, int toPos,
                        int dice1, int dice2, String reason) {
        moves.add(new MoveEntry(turn, strategy, fromPos, toPos, dice1, dice2, reason));
    }

    /**
     *writes the log, gets moved to JSON file
     */
    public void writeToFile(String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("[\n");
            for (int i = 0; i < moves.size(); i++) {
                MoveEntry m = moves.get(i);
                fw.write("  {\n");
                fw.write("    \"turn\": " + m.turn + ",\n");
                fw.write("    \"strategy\": \"" + m.strategy + "\",\n");
                fw.write("    \"from\": " + m.fromPos + ",\n");
                fw.write("    \"to\": " + m.toPos + ",\n");
                fw.write("    \"dice1\": " + m.dice1 + ",\n");
                fw.write("    \"dice2\": " + m.dice2 + ",\n");
                fw.write("    \"reason\": \"" + m.reason + "\"\n");
                fw.write("  }");
                if (i < moves.size() - 1) {
                    fw.write(",");
                }
                fw.write("\n");
            }
            fw.write("]\n");
        }
    }
}
