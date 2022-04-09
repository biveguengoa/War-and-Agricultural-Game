package game.gameTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.Game;
import game.TypePlayers.FarmerPlayer;
import game.boardTypes.FarmersBoard;
import game.characters.Farmer;
import game.exception.OceanDeniedCharacterException;
import game.exception.TileAlreadyOccupiedException;
import game.lands.FarmersTile;


/**
 * This class represents a farmer class game
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */
public class FarmerGame implements Game {
	
	private List<FarmerPlayer> players;
	private FarmersBoard myBoard;

	public FarmerGame(int boardWidth, int boardLength, List<FarmerPlayer> players) {
		this.myBoard = new FarmersBoard(boardWidth, boardLength);
		this.players = players;
	}
	
	/**
	 * Returns the player corresponding the given index in the parameter
	 * @param i the index of the player we will get
	 * @return returns the the corresponding player given by its index in the parameter
	 */
	public FarmerPlayer getPlayer(int i) {
		return this.players.get(i);
	}
	
	/**
	 * returns the number of player
	 * @return the number of player
	 */
	public int getNbPlayer() {
		return this.players.size();
	}
	
	@Override
	/**
	 * check if the the game is over
	 * @return true if all the tile in the board are all occupied so the game will finish
	 */
	public boolean isOver() {
		return this.myBoard.allOccupied();
	}

	@Override
	/**
	 * The method we call when we play
	 */
	public void play() {
		this.myBoard.displayBoard();
		for(FarmerPlayer player : this.players){
			
			System.out.println(player.getName().toUpperCase()+ " joue ");
			
			Random random = new Random();
			int rand = random.nextInt(3);
			if (rand == 0) {
				int randRow = (int) (1 + (this.myBoard.getWidth() - 1) * Math.random());
				int randColumn = (int) (1 + (this.myBoard.getLength() - 1) * Math.random());
				FarmersTile currentTile = (FarmersTile) this.myBoard.getTile(randRow, randColumn);
				Farmer currentFarmer = (Farmer) player.mission();
				try {
					currentFarmer.deployed(currentTile);
					player.addTile(currentTile);
	            	System.out.println(player.toString()+
	            			" deploie un ouvrier sur"+currentTile.toString()+"("+randColumn+","+randRow+")");
	            	
	            }catch(OceanDeniedCharacterException e) {
	            	System.out.println("Sorry, you cannot deploy an army in ocean");
	            }catch(TileAlreadyOccupiedException e) {
	            	System.out.println("Tile is already occupied !");
	            }
				
			} 
			else if(rand == 1) {
				System.out.println(player.toString()+"\n préfère d'échanger des ressources contre de l’or");
				player.exchangeRessources();
			}else {
				System.out.println(player.toString()+"\n ne fait rien");
				player.pass();
			}
        	player.getResource();
			System.out.println(player.getName()+" récolte des ressouces");
    		System.out.println(player.getName()+" paie ses ouvriers");
        	player.pay();
        	System.out.println("Liste tiles : [ "+player.displayTiles()+" ]");
        	System.out.println("------------");
		}
		
	}

	@Override
	/**
	 * Method is used to get the score of the player corresponding the given parameter
	 * @param index the indexed player
	 */
	public int getScorePlayer(int index) {
		int score = 0;
		score += this.getPlayer(index).getGold();
		int size = this.getPlayer(index).getNumberOfTiles();
		FarmersTile tile = null;
		for (int i = 0; i < size; i++) {
			tile = this.getPlayer(index).getTile(i);
			if (tile != null) {
				score += tile.getFarmer().getGold();
			}
		}

		return score;
	}
	
	/**
	 * Returns the list of players that have the maximum score
	 * @return the list of players that have the maximum score
	 */
	public List<FarmerPlayer> getWinner() {
		int max = this.getScorePlayer(0);
		for (int i = 0; i < this.getNbPlayer(); i++) {
			if (this.getScorePlayer(i) >= max) {
				max = this.getScorePlayer(i);
			}
		}
		// recherhe des joueurs ayant ce maximum
		List<FarmerPlayer> lst = new ArrayList<>();
		for (int i = 0; i < this.getNbPlayer(); i++) {
			if (this.getScorePlayer(i) == max) {
				lst.add(this.players.get(i));
			}
		}
		return lst;
	}
	

}
