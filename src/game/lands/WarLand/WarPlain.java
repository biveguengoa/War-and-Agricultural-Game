package game.lands.WarLand;

import game.characters.Military;
import game.lands.WarTile;

/**
 * DFefines a tile of type Plain
 * @author BIVEGUE Isabelle, KEBE Moustapha, BENIDER Imad and AICI Halima
 * @version 1.0
 */

public class WarPlain extends WarTile {

	/**
	 * Creates a tile of type Plain with a given character
	 * @param m character on this tile
	 */
	public WarPlain(Military m) {
		super(m);
	}

	/**
	 * Constructor of this class that creates a tile of type Plain
	 */
	public WarPlain() {
		super("Plain");
	}
	

	@Override
	public String toString() {
		return "[P]";
	}

}
