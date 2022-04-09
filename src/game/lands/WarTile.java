package game.lands;

import game.Tile;
import game.characters.Military;
import game.exception.TileAlreadyOccupiedException;
import game.exception.WarriorSizeException;

/**
 * Models a war tile defined by the character occupying it and 
 * its land.
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */

public abstract class WarTile extends Tile {

	protected Military military;

	/**
	 * Constructor that creates a tile
	 */
	public WarTile() {
		super("War Tile");
		this.military = null;
	}

	/**
	 * Creates a tile with initially the given <code>land</code>
	 * @param land this tile's land
	 */
	public WarTile(String land) {
		super(land);
		this.military = null;
		this.setResources(this.generateResources());
	}

	/**
	 * Creates a tile with  initially the given character 
	 * @param m the character on this tile
	 */
	public WarTile(Military m) {
		this();
		this.military = m;
	}

	/**
	 * Returns the character on this tile
	 * @return the character on this tile
	 */
	public Military getMilitary() {
		return this.military;
	}
	
	@Override
	public int generateResources() {
		return (int) (1 + 6 * Math.random());
	}
	
	/**
	 * Changes the character on this tile if there was no character before
	 * @param m the character to place on this tile
	 * @throws TileAlreadyOccupiedException if and only iff this tile is already occupied
	 */
	public void setMilitary(Military m) throws TileAlreadyOccupiedException {
		if (this.isOccupied())
			throw new TileAlreadyOccupiedException("Tile already occupied");
		this.military = m;
	}

	/**
	 * Applies effect on this tile when the military occupying this tile is not the 
	 * military's current player
	 * @param m military
	 * @return this troop new size
	 */
	public int applyEnemyEffect(Military m) {
		int newSize = 0;
		try {
			if (this.military.getNumberOfWarriors() > m.getNumberOfWarriors()) {
				newSize = (int) m.getNumberOfWarriors() / 2;
				m.changeTroop(newSize);
				if (newSize <= 1) {
					System.out.println(this.toString()+" is occupied by "+this.military.getMilitaryName());
					System.out.println(m.getMilitaryName() + " is captured");
					System.out.println(this.military.getMilitaryName() + " wins 2 pieces of gold");
					this.military.gainGold(2);

				} else
					newSize = 0;
			}else {
				System.out.println("No effect applied");
			}
		}catch(NullPointerException e) {
			System.out.println(this.toString()+" is not occupied");
		}
		

		return newSize;
	}

	/**
	 * Applies effect on this tile when the military occupying this tile is an ally
	 * @param m military
	 */
	public void applyAlliedEffect(Military m) {
		try {
			if (this.military.getNumberOfWarriors() < m.getNumberOfWarriors()) {
				try {
					System.out.println(this.toString()+" is occupied by "+this.military.getMilitaryName());
					m.changeTroop(m.getNumberOfWarriors() + 1);
					System.out.println(m.getMilitaryName() + " changes troop and become a troop of "
							+ m.getNumberOfWarriors() + " warriors");
					m.gainGold(1);
					System.out.println(m.getMilitaryName() + " wins a piece of gold");
					m.gainGold(1);
				} catch (WarriorSizeException e) {
					System.out.println("Size of a troop should be less than 5");
				}
			}else {
				System.out.println("No effect applied in "+this.toString());
			}
			
		}catch(NullPointerException e) {
			System.out.println(this.toString()+" is not occupied");
		}
	
	}
	
	@Override
	public boolean isOccupied() {
		return this.getMilitary() != null;
	}

}
