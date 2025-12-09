package monopoly;

import java.util.Random;
/**
 * Class that runs the game with its main method, contains both strategies A and B and prints out 
 * the statistics for a certain amount of turns for each strategy. The user must change the totalTurns variable 
 * in order to run more or less turns.
 * 
 * @author Guillermo Nasif
 */
public class play {

    // board setup
    static board[] newGame = {
        new board("GO", 0),
        new board("Mediterranean Avenue", 0),
        new board("Community Chest", 0),
        new board("Baltic Avenue", 0),
        new board("Income Tax", 0),
        new board("Reading Railroad", 0),
        new board("Oriental Avenue", 0),
        new board("Chance", 0),
        new board("Vermont Avenue", 0),
        new board("Connecticut Avenue", 0),
        new board("Jail / Just Visiting", 0),
        new board("St. Charles Place", 0),
        new board("Electric Company", 0),
        new board("States Avenue", 0),
        new board("Virginia Avenue", 0),
        new board("Pennsylvania Railroad", 0),
        new board("St. James Place", 0),
        new board("Community Chest", 0),
        new board("Tennessee Avenue", 0),
        new board("New York Avenue", 0),
        new board("Free Parking", 0),
        new board("Kentucky Avenue", 0),
        new board("Chance", 0),
        new board("Indiana Avenue", 0),
        new board("Illinois Avenue", 0),
        new board("B. & O. Railroad", 0),
        new board("Atlantic Avenue", 0),
        new board("Ventnor Avenue", 0),
        new board("Water Works", 0),
        new board("Marvin Gardens", 0),
        new board("Go to Jail", 0),
        new board("Pacific Avenue", 0),
        new board("North Carolina Avenue", 0),
        new board("Community Chest", 0),
        new board("Pennsylvania Avenue", 0),
        new board("Short Line Railroad", 0),
        new board("Chance", 0),
        new board("Park Place", 0),
        new board("Luxury Tax", 0),
        new board("Boardwalk", 0)
    };

    static Random rand = new Random();


    public static void main(String[] args) {

        // change this number for any run
        int totalTurns = 1_000;

        // run Strategy A
        resetVisits();
        playGameA(totalTurns);
        printStatistics("A", totalTurns);

        // run Strategy B
        resetVisits();
        playGameB(totalTurns);
        printStatistics("B", totalTurns);
    }


    /**
     * Helper method that resets visited times of each space to zero
     */
    private static void resetVisits() {
        for (board b : newGame) {
            b.setTimesVisited(0);
        }
    }

    /**
     * Dice method that gets a random number from 1 to 6.
     * @return the random number (simulating a dice)
     */
    private static int dice() {
        return rand.nextInt(6) + 1;
    }


    // prints visit count and probabilities
    private static void printStatistics(String strategy, int turns) {
        System.out.println("\nStrategy " + strategy + " results after " + turns + " turns:");
        System.out.println("------------------------------------------------");
        System.out.println("Idx\tSquare\t\t\tVisits\tPercent");

        double t = turns;

        for (int i = 0; i < 40; i++) {
            int visits = newGame[i].getTimesVisited();
            double pct = (visits / t) * 100.0;

            System.out.printf("%2d\t%-20s\t%7d\t%6.3f%%\n",
                    i,
                    newGame[i].getSpaceName(),
                    visits,
                    pct);
        }
    }


    /**
     * Strategy A: leave jail immediately (GOOJ or paying)
     * @param totalTurns number of turns the user inputs
     */
    public static void playGameA(int totalTurns) {

        Deck chance = CardFactory.createChanceDeck();
        Deck chest  = CardFactory.createCommunityChestDeck();
        PlayerState p = new PlayerState();
        Card goojCard = null;

        int pos = 0;

        for (int t = 1; t <= totalTurns; t++) {

            int d1 = dice();
            int d2 = dice();

            if (p.inJail) {

                if (p.hasGOOJ) { //player has a GOOJ card, uses the card and moves
                    p.hasGOOJ = false;

                    if (goojCard.isFromCommunityChest()) chest.returnGOOJCard(goojCard);
                    else chance.returnGOOJCard(goojCard);

                    goojCard = null;
                    p.inJail = false;
                    pos = (pos + d1 + d2) % 40;
                }
                else { //player doesnt have GOOJ card, pays the fine and moves
                    p.inJail = false;
                    pos = (pos + d1 + d2) % 40;
                }
            }

            else { //players isn't in jail, then moves
                pos = (pos + d1 + d2) % 40;

                if (d1 == d2) {
                    p.doublesCounter++;
                    if (p.doublesCounter == 3) { //3 doubles in a row
                        pos = 10;
                        p.inJail = true; // goes to jail
                        p.doublesCounter = 0; // reset double counter
                    }
                } else p.doublesCounter = 0;
            }
            
            newGame[pos].setTimesVisited(newGame[pos].getTimesVisited() + 1); //update visited times on each space

            if (pos == 30) { // if player falls in space #30, goes to jail
                pos = 10;
                p.inJail = true;
            }

            if (pos == 7 || pos == 22 || pos == 36) { // chance spaces
                Card c = chance.drawCard();
                pos = CardEffects.apply(c, pos, p, chance, chest);
                if (c.isGOOJ()) goojCard = c;
            }

            if (pos == 2 || pos == 17 || pos == 33) { //community chest spaces
                Card c = chest.drawCard();
                pos = CardEffects.apply(c, pos, p, chance, chest);
                if (c.isGOOJ()) goojCard = c;
            }

            
        }
    }



    /**
     * Strategy B: try rolling doubles 3 times before paying
     * @param totalTurns number of turns the user inputs
     */
    public static void playGameB(int totalTurns) {

        Deck chance = CardFactory.createChanceDeck();
        Deck chest  = CardFactory.createCommunityChestDeck();
        PlayerState p = new PlayerState();
        Card goojCard = null;

        int pos = 0;

        for (int t = 1; t <= totalTurns; t++) {

            int d1 = dice();
            int d2 = dice();

            if (p.inJail) {

                if (p.hasGOOJ) {
                    p.hasGOOJ = false;

                    if (goojCard.isFromCommunityChest()) chest.returnGOOJCard(goojCard);
                    else chance.returnGOOJCard(goojCard);

                    goojCard = null;
                    p.inJail = false;
                    pos = (pos + d1 + d2) % 40;
                }

                else if (d1 == d2) { // player doesn't have a GOOJ card, tries to get a double, up to 3 consecutive times
                    p.inJail = false;
                    pos = (pos + d1 + d2) % 40;
                    p.failedDoubleAttempts = 0;
                }

                else {
                    p.failedDoubleAttempts++;
                    if (p.failedDoubleAttempts == 3) {
                        p.inJail = false;
                        p.failedDoubleAttempts = 0;
                        pos = (pos + d1 + d2) % 40;
                    }
                }
            }

            else {
                pos = (pos + d1 + d2) % 40;

                if (d1 == d2) {
                    p.doublesCounter++;
                    if (p.doublesCounter == 3) {
                        pos = 10;
                        p.inJail = true;
                        p.doublesCounter = 0;
                    }
                } else p.doublesCounter = 0;
            }
           
            newGame[pos].setTimesVisited(newGame[pos].getTimesVisited() + 1);
            
            if (pos == 30) {
                pos = 10;
                p.inJail = true;
            }

            if (pos == 7 || pos == 22 || pos == 36) {
                Card c = chance.drawCard();
                pos = CardEffects.apply(c, pos, p, chance, chest);
                if (c.isGOOJ()) goojCard = c;
            }

            if (pos == 2 || pos == 17 || pos == 33) {
                Card c = chest.drawCard();
                pos = CardEffects.apply(c, pos, p, chance, chest);
                if (c.isGOOJ()) goojCard = c;
            }

           
        }
    }
}
