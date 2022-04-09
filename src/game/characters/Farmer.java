package game.characters;

import game.Character;
import game.Tile;
import game.exception.TileAlreadyOccupiedException;
import game.lands.FarmersTile;

/**
 * This class represents a character of type Farmer 
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */

public class Farmer extends Character {
	
	/**
	 * Creates an instance of Farmer class
	 */
	public Farmer() {
		super();
	}

	@Override
	public int getPaid(Tile tile) {
		this.gainGold(((FarmersTile) tile).getPaiment());
		return ((FarmersTile) tile).getPaiment();
	}

	@Override
	public void deployed(Tile tile) {
		try {
			((FarmersTile) tile).setFarmer(this);
		}
		catch(TileAlreadyOccupiedException e) {
			System.out.println("Tile already occupied");
		}
		
	}

	@Override
	public int collectResource(Tile tile) {
		return ((FarmersTile) tile).generateResources();
	}

}
