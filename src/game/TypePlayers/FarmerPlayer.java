package game.TypePlayers;

import java.util.ArrayList;
import java.util.List;

import game.Character;
import game.Player;
import game.Tile;
import game.characters.Farmer;
import game.lands.FarmersTile;
import game.lands.FarmersLand.FarmerDesert;
import game.lands.FarmersLand.FarmerPlain;
import game.lands.FarmersLand.FarmerWoodland;

/**
 * Represents a player who plays the agricultural game defined by the tiles owned
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */

public class FarmerPlayer extends Player {
	
	protected List<FarmersTile> myTiles;
	
	/**
	 * Creates a farmer player of given <code>name</code> with initially 15 golden coins
	 * @param name this player's name
	 */
	public FarmerPlayer(String name) {
		super(name);
		this.gold = 15;
		this.myTiles = new ArrayList<>();
	}
	
	/**
	 * get the number of tile in this player's list of tiles
	 * @return the number of tiles got by of this player 
	 */
	public int getNumberOfTiles() {
		return this.myTiles.size();
	}
	
	/**
	 * get the tile given by its index in the parameter
	 * @param i the index of the tile
	 * @return the tile in the index given
	 */
	public FarmersTile getTile(int i) {
		return this.myTiles.get(i);
	}
	
	/**
	 * Method called when this player wants to add a tile in his list of tiles
	 */
	public void addTile(FarmersTile tile) {
		this.myTiles.add(tile);
	}
	
	/**
	 * remove the tile
	 * @param i the index of the tile that will be removed
	 */
	public void removeTile(int i) {
		this.myTiles.remove(i);
	}
	
	/**
	 * get the farmer given by its index in the parameter
	 * @param i the index of the farmer
	 * @return the farmer in the index given
	 */
	public Farmer getFarmer(int i) {
		return this.myTiles.get(i).getFarmer();
	}


	@Override
	/**
	 * This method is called after every round to collect resources 
	 * into tiles that are occupied by this player's farmers
	 */
	public int getResource() {
		for(int i=0; i<this.getNumberOfTiles(); i++) {
			try {
				this.resource += this.getTile(i).getFarmer().collectResource(this.getTile(i)) ;
			}catch(NullPointerException e) {
				System.out.println("No more farmers to collect ressources");
			}
		}
		return this.resource;
	}
	/**
	 * After this player make an action, he needs to pay its farmers and this method is called for that
	 */
	public void pay() {
		for(int i=0; i<this.getNumberOfTiles(); i++) {
			try {
				int amount = this.getFarmer(i).getPaid(this.getTile(i));
				if(this.getGold()>0 && this.getGold()>amount) {
					this.gold -= amount;
					System.out.println("Ouvrier occupant "+this.getTile(i).toString()+" est payé de "+amount+" pièce(s) d'or ");
				}else {
					System.out.println(this.getName().toUpperCase()+" perd "+this.getTile(i).toString());
					this.removeTile(i);
				}
			}
			catch(NullPointerException e) {
				System.out.println("You have no tiles");
			}
		}
		
	}
	
	/**
	 * Method is called when the player prefer to exchage his ressources to gold
	 */
	public void exchangeRessources() {
		for(int i=0; i<this.getNumberOfTiles(); i++) {
			try {
				if(this.getResource()>0) {
					this.resource -= 1;
					this.gold += this.getTile(i).exchangeToGold();
				}else {
					System.out.println("No more ressources to exchange");
				}
				
			}
			catch(NullPointerException e) {
				this.resource +=1;
				System.out.println("You have no tiles");
			}
		}
	}

	@Override
	/**
	 * It is called when converting ressources to gold
	 */
	public void convertResources(Tile t) {
		this.gold += ((FarmersTile)t).exchangeToGold();
	}
	
	/**
	 * This method is called when this play decide to not to play.Do he can gain some gold
	 */
	public void pass() {
		for(int i=0; i<this.getNumberOfTiles(); i++) {
			try {
				if((this.getTile(i) instanceof FarmerPlain) || (this.getTile(i) instanceof FarmerWoodland))
					this.gold += 1;
				else if(this.getTile(i) instanceof FarmerDesert)
					this.gold += 2;
			}
			catch(NullPointerException e) {
				System.out.println("You have no tile");
			}
		}
	}

	@Override
	/**
	 * This method is called when the player is ready to deploy a farmer into an tile
	 */
	public Character mission() {
		return new Farmer();
	}

	@Override
	public String toString() {
		return  "\nPlayer : Nom : " + this.getName().toUpperCase()+
				" Ressources : "+this.getResource()+
				" Or : " + this.gold +
				", ouvriers déployés : "+ 
				this.getNumberOfTiles()+" ";
	}
	
	public String displayTiles() { 
        String s="";
        for(int i=0;i<this.getNumberOfTiles();i++) {
            s += this.getTile(i).toString()+" - ";
        }
            
        return s;
    }
	

}
