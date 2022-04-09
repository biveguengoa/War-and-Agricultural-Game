package game.lands.WarLand;

import game.characters.Military;
import game.lands.WarTile;

/**
 * Defines a tile of type Woodland
 * @author BIVEGUE Isabelle, KEBE Moustapha, BENIDER Imad and AICI Halima
 * @version 1.0
 */

public class WarWoodland extends WarTile {

	/**
	 * Creates a tile of type Woodland with a given character
	 * @param m military on this tile
	 */
	public WarWoodland(Military m) {
		super(m);
	}

	/**
	 * Constructor of this class that creates a tile of type Woodland
	 */
	public WarWoodland() {
		super("Woodland");
	}
	

	
	@Override
	public String toString() {
		return "[W]";
	}

}