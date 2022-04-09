package game;


/**
 * This class represents any kind of character
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */

public abstract class Character {

	protected int gold;

	/**
	 * Creates a character with initally 0 gold
	 */
	public Character() {
		this.gold = 0;
	}
	
	/**
	 * Provides this character's gold
	 * @return this character's gold
	 */
	public int getGold() {
		return this.gold;
	}
	
	/**
	 * This abstract method is used when a character is paid according to the tile where it works
	 * @param tile the tile where this character is deployed
	 * @return the payment of this character
	 */
	public abstract int getPaid(Tile tile);
	
	/**
	 * This abstract method is called when deploying this character in the tile given in the parameter
	 * @param tile the tile where this character is deployed
	 */
	public abstract void deployed(Tile tile);
	
	/**
	 * This abstract method helps to collect resources from the tile given in the parameter
	 * @param tile the tile where we collect resources from
	 * @return the resource collected from the tile in the parameter
	 */
	public abstract int collectResource(Tile tile);

	
	/**
	 * This method is called to store this character's gold
	 * @param gold gold to be added
	 */
	public void gainGold(int gold) {
		this.gold += gold;
	}
}
