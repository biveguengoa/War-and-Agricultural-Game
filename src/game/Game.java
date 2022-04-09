package game;

/**
 * This interface represents a game
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */
public interface Game 
{
	
	/**
	 * Check if this game is over
	 * @return true if this game is over
	 */
    public boolean isOver();

    /**
     * Method used to play a game. Any game implements this interface has to implements this play method.
     */
    public void play();
    
    
    /**
     * Called to get the score of a player
     * @param index the index corresponding the player we wish to get its score
     * @return the score of the indexed player
     */
	public int getScorePlayer(int index);
	
	
}