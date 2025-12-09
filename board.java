package monopoly;
/**
 * Represents a single space on the Monopoly board. Each board object stores the name of the square and
 * how many times the player has landed on it during the simulation.  Used for tracking visit counts and
 * calculating landing probabilities.
 * @author Guillermo Nasif
 */
public class board {

	public String spaceName;
	public int timesVisited;
	
	public board(String spaceName, int timesVisited) {
		this.spaceName = spaceName;
		this.timesVisited = timesVisited;
	}

	public int getTimesVisited() {
		return timesVisited;
	}

	public void setTimesVisited(int timesVisited) {
		this.timesVisited = timesVisited;
	}

	public String getSpaceName() {
		return spaceName;
	}
	
	@Override
	public String toString() {
		return spaceName + " - visited : " + timesVisited + " times" ;
	}
	
	
}
