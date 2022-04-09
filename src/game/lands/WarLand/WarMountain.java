package game.lands.WarLand;

import game.characters.Military;
import game.exception.TileAlreadyOccupiedException;
import game.exception.WarriorSizeException;
import game.lands.WarTile;

/**
 * Defines a tile of type Mountain
 * @author BIVEGUE Isabelle, KEBE MOustapha, BENIDER Imad and AICI Halima
 * @version 1.0
 */

public class WarMountain extends WarTile {

	/**
	 * Creates a tile of type Mountain with a given character
	 * @param m character on this tile
	 */
	public WarMountain(Military m) {
		super(m);
	}

	/**
	 * Constructor of this class that creates a tile of type Mountain
	 */
	public WarMountain() {
		super("Mountain");
	}
	
	
	@Override
	public void setMilitary(Military m) throws WarriorSizeException, TileAlreadyOccupiedException 
	{
		if(this.isOccupied())
			throw new TileAlreadyOccupiedException("Tile already occupied");
		if( m.getNumberOfWarriors() <= 3 ) {
			this.military = m;
		}else {
			throw new  WarriorSizeException("Mountain tile can't take more than 3 militaries");
		}
	}
	
	
	
	@Override
	public int applyEnemyEffect(Military m) {
		try{
			this.military.changeTroop(this.military.getNumberOfWarriors()+ 2 );
		}
		catch(WarriorSizeException e) {
			System.out.println("Size does not exist");
		}
		catch(NullPointerException e) {
			System.out.println(this.toString()+" is not occupied");
		}
		int newSize = 0;
		try{
			if(this.military.getNumberOfWarriors() > m.getNumberOfWarriors())
			{
				newSize = (int)m.getNumberOfWarriors()/2;
				this.military.changeTroop(newSize);
				if(newSize < 1) 
				{
					if(this.military.getNumberOfWarriors() + newSize <3) {
						System.out.println(this.toString()+" is occupied by "+this.military.getMilitaryName());
						System.out.println(this.military.getMilitaryName()+" is captured");
						m.gainGold(2);
						System.out.println(m.getMilitaryName()+" wins 2 pieces of gold");
				newSize = (int) m.getNumberOfWarriors()/2;
				m.changeTroop(newSize);
				if(newSize <= 1) {
					if(this.military.getNumberOfWarriors() + newSize < 3) {
						System.out.println(m.getMilitaryName()+" is captured");
						System.out.println(this.military.getMilitaryName()+" wins 2 pieces of gold");
						this.military.gainGold(2);
						
					}else {
						System.out.println(this.toString()+" cannot support more than 3 militaries");
					}
					
				}
					}
				}
				newSize = 0;
			}
		}catch(NullPointerException e) {
			System.out.println(this.toString()+" is not occupied");
		}
		
		return newSize;
	}
	
	@Override
	public void applyAlliedEffect(Military currentMilitary) 
	{
		try {
			if(this.military.getNumberOfWarriors() < currentMilitary.getNumberOfWarriors())
			{
				if(this.military.getNumberOfWarriors() + 1 <= 3) {
					System.out.println(this.toString()+" is occupied by "+this.military.getMilitaryName());
					currentMilitary.changeTroop(currentMilitary.getNumberOfWarriors() + 1);
					System.out.println(currentMilitary.getMilitaryName()+" changes troop and become a troop of "
													  +currentMilitary.getNumberOfWarriors()+" warriors");
					currentMilitary.gainGold(1);
					System.out.println(currentMilitary.getMilitaryName()+" wins a piece of gold");
					
				}else {
					System.out.println(this.toString()+" cannot support more than 3 militaries");
				}
			}
		}catch(NullPointerException e) {
			System.out.println(this.toString()+" is not occupied");
		}
	}
	

	@Override
	public String toString() {
		return "[M]";
	}

}