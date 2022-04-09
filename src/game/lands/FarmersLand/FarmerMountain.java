package game.lands.FarmersLand;

import game.lands.FarmersTile;

/**
 * Defines a tile of type Mountain
 * @author BIVEGUE Isabelle, KEBE Moustapha, BENIDER Imad and AICI Halima
 * @version 1.0
 */

public class FarmerMountain extends FarmersTile {
	
	
	/**
	 * Creates a tile of type Mountain
	 */
	public FarmerMountain() {
		super("Mountain tile");
	}

	/**
	 * Creates a tile of type Mountain with given land
	 * @param land this tile's land
	 */
	public FarmerMountain(String land) {
		super(land);
	}
	
	@Override
	public int getPaiment() {
		return 5;
	}

	@Override
	public int exchangeToGold() {
		return 8;
	}

	@Override
	public String toString() {
		return "[M]";
	}
	

}
