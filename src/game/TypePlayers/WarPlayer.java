package game.TypePlayers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.Character;
import game.Player;
import game.Tile;
import game.characters.Military;
import game.exception.NoMoreWarriorsToDeployException;
import game.lands.WarTile;
import game.lands.WarLand.WarPlain;
import game.lands.WarLand.WarWoodland;

/**
 * Defines a war player represented by his number of warriors, the stock of food,
 * the number of territories owned, his troops and tiles
 * @author BIVEGUE Isabelle, KEBE Moustapha, BENIDER Imad and AICI Halima
 * @version 1.0
 */

public class WarPlayer extends Player{
	
	protected int foods;
	protected int numberOfWarriors;
	protected List<Military> myTroops;
	protected int numberOfTerritory;
	protected List<WarTile> myTiles;

	/**
	 * Creates a war player of given <code>name</code> with initially 10 stock 
	 * of food, 35 number of warriors and no territory owned
	 * @param name this player's name
	 */
	public WarPlayer(String name) {
		super(name);
		this.foods = 10;
		this.numberOfWarriors = 35;
		this.myTroops = new ArrayList<>();
		this.myTiles = new ArrayList<>();
		this.numberOfTerritory = 0;
	}
	
	/**
	 * Allows warriors of this player's troop to collect resource from tiles
	 */
	@Override
	public int getResource() 
	{
		for(int i=0; i<this.myTiles.size(); i++) {
			try {
				this.resource += myTiles.get(i).getMilitary().collectResource(myTiles.get(i));
				this.convertResources(myTiles.get(i));
			}catch(NullPointerException e) {
				e.getMessage();
			}
		}
		return this.resource;
	}
	@Override
	public void convertResources(Tile t) {
		if(t instanceof WarWoodland) {
			this.foods += 1;
		}
		if(t instanceof WarPlain) {
			this.foods += 5;
		}
	}
	
	/**
	 * Provides this player's stock of warriors
	 * @return the number of warriors of this player
	 */
	public int getNumberOfWarriors() {
		return this.numberOfWarriors;
	}

	/**
	 * Enables to add <code>nbOfWarriors</code> xarriors to this player's actual
	 * army
	 * @param nbOfWarriors th enumber of warriors to be added
	 */
	public void setNumberOfWarriors(int numberOfWarriors) {
		this.numberOfWarriors += numberOfWarriors;
	}
	
	/**
	 * Returns all the tiles owned by this player
	 * @return the tiles owned by this player
	 */
	public List<WarTile> getMyTiles() {
		return this.myTiles;
	}

	/**
	 * Returns all the troops owned by this player
	 * @return the troops owned by this player 
	 */
	public List<Military> getMyTroops() {
		return this.myTroops;
	}

	/**
	 * Provides the number of territories acquired by this player
	 * @return the number of territories of this player 
	 */
	public int getNumberOfTerritory() {
		return this.numberOfTerritory;
	}

	/**
	 * Increases the number of territories owned by this player
	 * by <code>territory</code>
	 * @param territory the number of territory to be added
	 */
	public void setNumberOfTerritory(int numberOfTerritory) {
		this.numberOfTerritory += numberOfTerritory;
	}

	/**
	 * Gives the stock of food owned by this player
	 * @return this player's stock food
	 */
	public int getFoods() {
		if(this.foods >=0)
			return this.foods;
		return 0;
	}
	
	/**
	 * Captures an enemy when needed and increases this player's number of warriors by <code>n</code>
	 * @param n the number of warriors to capture
	 */
	public void captureEnemy(int n) {
		System.out.println(this.getName()+" a capturé son énemie");
		this.numberOfWarriors += n;
	}

	
	/**
	 * Returns <code>true</code> if and only if the given character is an enemy (it 
	 * does not belong to this player's army) otherwise, <code>false</code> 
	 * @param m the character to check
	 * @return <code>true</code> if the character does nto belong to this player's army
	 */
	public boolean isAnEnemy(Military m) {
		for(int i = 0; i < this.myTiles.size(); i++) {
			if(this.getMyTiles().get(i).getMilitary().getMilitaryName().equals(m.getMilitaryName())) {
				return true;
			}
		} 
		return false;
	}
	
	/**
	 * Enables to feed every troop of this player's army if there's food left. If the player cannot
	 * feed one of his troops, this troop will be removde and the player will gain <code>1</code> gold
	 */
	public void feeds() {
		for(int i=0; i<this.myTiles.size(); i++) {
			if(this.myTiles.get(i).getMilitary() != null) {
				int amount = this.myTiles.get(i).getMilitary().getPaid(this.myTiles.get(i));
				if(this.getFoods()>0 && this.getFoods()>amount) {
					this.foods -= amount;
					System.out.println(this.myTiles.get(i).getMilitary().getMilitaryName()+" avec "+this.myTiles.get(i).getMilitary().getNumberOfWarriors()+" guerriers, "
						+" coût "+amount+" a été nourri de "+amount);
					//System.out.println("-------------------------------------------\n");
				}else {
					System.out.println(this.getName().toUpperCase()+" loose "+this.myTiles.get(i).getMilitary().getMilitaryName());
					System.out.println(this.getName().toUpperCase()+" loose "+this.myTiles.get(i));
					System.out.println(this.getName().toUpperCase()+" gain 1 gold");
					this.myTroops.remove(this.myTiles.get(i).getMilitary());
					this.myTiles.remove(i);
					this.setGold(1);
				}
			}
		}
	}
	
	/**
	 * Enables this player to send a troop of <code>number</code> warriors
	 *  if there are enough warriors left
	 * @param number the number of warrior to deploy
	 * @return the troop that have been deployed
	 * @throws NoMoreWarriorsToDeployException if there is no more warriors to send
	 */
	public Character sendTroop(int number ) throws NoMoreWarriorsToDeployException
	{
		Military troop = null;
		if(this.numberOfWarriors > 0 && (number>0 && number<= this.numberOfWarriors)) {
			this.numberOfWarriors -= number;
			troop = new Military(number,this.getName()+"_army_"+number );
		}else {
			throw new NoMoreWarriorsToDeployException("No more warriors to deploy !");
		}
		return troop;
	}
	
	
	@Override
	public Military mission() {

		Random random = new Random();
		int randChoice = random.nextInt(4);
		Military troop = null;

		switch (randChoice) {
		case 0: {
			try {
				troop = (Military) this.sendTroop(1);
			}catch(NoMoreWarriorsToDeployException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 1: {
			try {
				troop = (Military) this.sendTroop(2);
			}catch(NoMoreWarriorsToDeployException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 2: {
			try {
				troop = (Military) this.sendTroop(3);
			}catch(NoMoreWarriorsToDeployException e) {
				System.out.println(e.getMessage());
			}

			break;
		}
		case 3: {
			try {
				troop = (Military) this.sendTroop(4);
			}catch(NoMoreWarriorsToDeployException e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		case 4: {
			try {
				troop = (Military) this.sendTroop(5);
			}catch(NoMoreWarriorsToDeployException e) {
				System.out.println(e.getMessage());
			}
			break;
		}

		}

		return troop;
	}


	
	@Override
	public String toString() {
		return  "\nPlayer : Nom : " + this.getName().toUpperCase() + ", nombre de guerriers : " + 
				this.numberOfWarriors + ", Nourritures : ="+ this.getFoods()+ 
				", Or : " + this.gold +", armées déployées : "+ this.myTroops.size()+" ";
	}
}
