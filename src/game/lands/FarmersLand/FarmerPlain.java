package game.lands.FarmersLand;

import game.lands.FarmersTile;

/**
 * Defines a tile of type Plain
 * @author BIVEGUE Isabelle, KEBE Moustapha, BENIDER Imad and AICI Halima
 * @version 1.0
 */

public class FarmerPlain extends FarmersTile{

	/**
	 * Creates a tile of type Plain
	 */
	public FarmerPlain() {
		super("Plain tile");
	}

	/**
	 * Creates a tile of type Plain with given land
	 * @param land this tile's land
	 */
	public FarmerPlain(String land) {
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
		return "[P]";
	}
	

}
