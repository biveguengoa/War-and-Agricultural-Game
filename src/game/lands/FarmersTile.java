package game.lands;

import game.Tile;
import game.characters.Farmer;
import game.exception.TileAlreadyOccupiedException;

/**
 * Models a farmer tile defined by the character occupying it and 
 * its land.
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */

public abstract class FarmersTile extends Tile {
		
	protected Farmer farmer;
	
	/**
	 * Creates a farmer tile
	 */
	public FarmersTile() {
		super("Farmer tile");
		this.farmer = null; 
	}

	/**
	 * Creates a farmer tile with the given <code>land</code>
	 * @param land 
	 */
	public FarmersTile(String land) {
		super(land);
		this.farmer = null;
	}
	
	/**
	 * Returns the character on this tile
	 * @return the character on this tile
	 */
	public Farmer getFarmer() {
		return this.farmer;
	}

	/**
	 * Changes the character on this tile if there was no character before
	 * @param farmer the character to place on this tile
	 * @throws TileAlreadyOccupied if and only iff this tile is already occupied
	 */
	public void setFarmer(Farmer farmer) throws TileAlreadyOccupiedException {
		if (this.isOccupied())
			throw new TileAlreadyOccupiedException("Tile already occupied");
		this.farmer = farmer;
	}

	@Override
	public boolean isOccupied() {
		return this.farmer != null;
	}

	@Override
	public int generateResources() {
		return 1;
	}
	
	/**
	 * Allows to get a payment according to the tile that is occupied
	 * @return this tile's payment amount
	 */
	public abstract int getPaiment();

	/**
	 * Enables to change a ressource into gold
	 * @return the amount of the exchange
	 */
	public abstract int exchangeToGold();

	
}
