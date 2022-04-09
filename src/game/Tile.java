package game;


/**
 * Models a tile defined by its resources, the character occupying it and its land.
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */

public abstract class Tile {

	protected int resources;
	protected String land;
	
	/**
	 * Constructor that creates a tile
	 */
	public Tile() {
		this.setResources(this.generateResources());
		this.land = "";
	}
	
	
	/**
	 * Creates a tile with initially the character <code>character</code>
	 * and given <code>land</code>
	 * @param character character on this tile
	 */
	public Tile(String land) {
		this.land = land;
		this.setResources(this.generateResources());
	}
	
	
	/**
	 * Generates a random amount of resources for this tile
	 * @return this tile resources
	 */
	public abstract int generateResources();

	/**
	 * Provides this tile number of resources available
	 * @return this tile number of resources available
	 */
	public int getResources() {
		return this.resources;
	}

	/**
	 * Enables to change this tile number of resources available with given resources
	 * @param resources the new number of resources available
	 */
	public void setResources(int resources) {
		this.resources = resources;
	}

	/**
	 * Provides this tile's land
	 * @return this tile's land
	 */
	public String getLand() {
		return this.land;
	}
	
	/**
	 * Returns <code>true</code> if and only if there is no character on this tile, 
	 * otherwise <code>false</code>
	 * @return <code>true</code> if this tile is occupied, otherwise <code>false</code>
	 */
	public abstract boolean isOccupied(); 
	
	/**
	 * Provides a string that describes this tile
	 * @return this tile's description
	 */
	public abstract String toString();

}
