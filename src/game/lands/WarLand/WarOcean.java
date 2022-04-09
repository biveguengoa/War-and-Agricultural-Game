package game.lands.WarLand;

import game.characters.Military;
import game.exception.OceanDeniedCharacterException;
import game.lands.WarTile;


/**
 * Defines a tile of type Ocean
 * @author BIVEGUE Isabelle, KEBE Moustapha, BENIDER Imad and AICI Halima
 * @version 1.0
 */
public class WarOcean extends WarTile {

	/**
	 * Constructor of this class that creates a tile of type Ocean
	 */
	public WarOcean() {
		super("Ocean");
	}
	
	
	public String toString() {
		return "[O]";
	}
	

	@Override
	public void setMilitary(Military m) throws OceanDeniedCharacterException {
		this.military = null;
		throw new OceanDeniedCharacterException("Ocean cannot take character");
	}

}
