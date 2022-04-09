package game.lands.FarmersLand;

import game.lands.FarmersTile;

/**
 * Defines a tile of type Woodland
 * @author BIVEGUE Isabelle, KEBE Moustapha, BENIDER Imad and AICI Halima
 * @version 1.0
 */

public class FarmerWoodland extends FarmersTile {

	/**
	 * Creates a tile of type Woodland
	 */
	public FarmerWoodland() {
		super("Woodland tile");
	}

	/**
	 * Creates a tile of type Woodland with given land
	 * @param land this tile's land
	 */
	public FarmerWoodland(String land) {
		super(land);
	}

	@Override
	public int getPaiment() {
		return 1;
	}

	@Override
	public int exchangeToGold() {
		return 2;
	}

	@Override
	public String toString() {
		return "[W]";
	}
	

}
