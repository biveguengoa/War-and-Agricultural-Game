package game;

/**
 * This abstract class represents a player defined by its name, its resources and gold
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */

public abstract class Player {
	
	protected String name;
	protected int gold;
	protected int resource;

	/**
	 * Constructor that creates a player with given <code>name</code>
	 * @param name this player's name
	 */		
	public Player(String name) {
		this.name = name;
		this.gold = 0;
		this.resource = 0;
	}
		
	/**
	 * Provides this player's name
	 * @return this player's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Provides the numbers of resources of this player
	 * @return this player's number of resources
	 */
	public abstract int getResource();

	
	/**
	 * This method is used to convert resource that we get from the tile in the parameter
	 * @param t the tile where we get its resource
	 */
	public abstract void convertResources(Tile t) ;
	

	/**
	 * Provides the golden coins owned by this player
	 * @return this player's amount of golden coin
	 */
	public int getGold() {
		return this.gold;
	}

	/**
	 * Increases this player stock of gold with given number
	 * @param gold number of gold to be added
	 */
	public void setGold(int gold) {
		this.gold += gold;
	}
	
	/**
	 * Changes this player's name with given <code>name</code>
	 * @param name this player's new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Method called when creating and deploying a character
	 * @return the character we just create and ready to get deployed
	 */
	public abstract Character mission();

	
	@Override
	/**
	 * To display the sequence of the game
	 */
	public abstract String toString() ;

	
}