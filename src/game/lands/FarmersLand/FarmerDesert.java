package game.lands.FarmersLand;

import game.lands.FarmersTile;

/**
 * Defines a tile of type Desert
 * @author BIVEGUE Isabelle, KEBE Moustapha, BENIDER Imad and AICI Halima
 * @version 1.0
 */

public class FarmerDesert extends FarmersTile {
	
	/**
	 * Creates a tile of type Desert
	 */
	public FarmerDesert() {
		super("Desert tile");
	}

	/**
	 * Creates a tile of type Desert with given land
	 * @param land this tile's land
	 */
	public FarmerDesert(String land) {
		super(land);
	}

	@Override
	public int getPaiment() {
		return 3;
	}

	@Override
	public String toString() {
		return "[D]";
	}


	@Override
	public int exchangeToGold() {
		return 5;
	}

}
