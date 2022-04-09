package game.characters;

import game.Character;
import game.Tile;
import game.exception.WarriorSizeException;
import game.lands.WarTile;
import game.lands.WarLand.WarDesert;

/**
 * This class represents a character of type Military defined by its number of warriors 
 * and its troop's name
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */


public class Military extends Character {

	protected int numberOfWarriors;
	protected String militaryName;
	
	/**
	 * Creates a military with given <code>number of warriors</code> and <code>troop
	 * name</code>
	 * @param numberOfWarriors this military troop's number of warriors
	 * @param militaryName this military troop's name
	 */
	public Military(int numberOfWarriors, String militaryName) {
		super();
		this.militaryName = militaryName;
		this.changeTroop(numberOfWarriors);
	}
	
	/**
	 * Provides this army's number of warriors
	 * @return this army's number of warriors
	 */
	public int getNumberOfWarriors() {
		return this.numberOfWarriors;
	}
	
	/**
	 * Changes this army's number of warriors 
	 * @param warriors the number of warriors to change
	 * @throws WarriorSizeException if the number of warriors to be added is less than
	 * 1
	 */
	public void changeTroop(int warriors) throws WarriorSizeException {
		if( warriors >= 1 && warriors <= 5 )
			this.numberOfWarriors = warriors;
		else if(warriors > 5 )
			this.numberOfWarriors = 5;
		else if(warriors < 1)
			throw new  WarriorSizeException("Troop doesn't exist");
	}
	
	
	@Override
	public void deployed(Tile tile) {
		try {
			((WarTile) tile).setMilitary(this);
		}
		catch(WarriorSizeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int collectResource(Tile tile) {
		return ((WarTile) tile).getResources();
	}
	
	/**
	 * Provides this army's name
	 * @return this army's name
	 */
	public String getMilitaryName() {
		return this.militaryName;
	}

	/**
	 * Replaces this army's name by the given name
	 * @param militaryName the new name to be given
	 */
	public void setMilitaryName(String militaryName) {
		this.militaryName = militaryName;
	}
	
	@Override
	public int getPaid(Tile tile) {
		if((WarTile)tile instanceof WarDesert) {
			this.gainGold(this.getNumberOfWarriors()*2);
			return this.getNumberOfWarriors()*2;
		}
		this.gainGold(this.getNumberOfWarriors());
		return this.getNumberOfWarriors();
	}
	
}
