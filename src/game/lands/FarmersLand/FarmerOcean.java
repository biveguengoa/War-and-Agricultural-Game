package game.lands.FarmersLand;

import game.characters.Farmer;
import game.exception.OceanDeniedCharacterException;
import game.lands.FarmersTile;

/**
 * Defines a tile of type Ocean
 * @author BIVEGUE Isabelle, KEBE Moustapha, BENIDER Imad and AICI Halima
 * @version 1.0
 */

public class FarmerOcean extends FarmersTile {

	/**
	 * Creates a tile of type Ocean
	 */
	public FarmerOcean() {
		super("Ocean tile");
	}

	/**
	 * Creates a tile of type Ocean with given land
	 * @param land this tile's land
	 */
	public FarmerOcean(String land) {
		super(land);
	}

	@Override
	public int getPaiment() {
		return 0;
	}

	@Override
	public int exchangeToGold() {
		return 0;
	}

	public String toString() {
		return "[O]";
	}
	
	@Override
	public void setFarmer(Farmer farmer) throws  OceanDeniedCharacterException {
		this.farmer = null;
		throw new OceanDeniedCharacterException("Ocean cannot take character");
	}
	

}
