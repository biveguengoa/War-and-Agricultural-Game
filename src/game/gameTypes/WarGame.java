package game.gameTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.Game;
import game.TypePlayers.WarPlayer;
import game.boardTypes.WarBoard;
import game.characters.Military;
import game.exception.NoMoreWarriorsToDeployException;
import game.exception.OceanDeniedCharacterException;
import game.exception.TileAlreadyOccupiedException;
import game.exception.WarriorSizeException;
import game.lands.WarTile;
import game.lands.WarLand.WarDesert;
import game.lands.WarLand.WarMountain;
import game.lands.WarLand.WarPlain;
import game.lands.WarLand.WarWoodland;
/**
 * This class represents a farmer class game
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */
public class WarGame implements Game {
	private List<WarPlayer> players;
	private WarBoard myBoard;

	public WarGame(int boardWidth, int boardLength, List<WarPlayer> players) {
		this.myBoard = new WarBoard(boardWidth, boardLength);
		this.players = players;
	}

	public List<WarPlayer> getPlayers() {
		return players;
	}

	/**
	 * returns the number of player
	 * @return the number of player
	 */
	public int getNbPlayer() {
		return this.players.size();
	}

	/**
	 * Returns the player corresponding the given index in the parameter
	 * @param i the index of the player we will get
	 * @return returns the the corresponding player given by its index in the parameter
	 */
	public WarPlayer currentPlayer(int i) {
		return this.players.get(i);
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
		for(WarPlayer player : this.players){
			
			System.out.println(player.getName().toUpperCase()+ " joue ");
			
			Random random = new Random();
			int rand = random.nextInt(2);
			if (rand == 0) {
				System.out.println(player.toString()+"\n ne fait rien");
			} else {
				deploy(player);
			}
        	player.getResource();
			System.out.println(player.getName()+" récolte des ressouces");
    		System.out.println(player.getName()+" nourrit ses armées");
        	player.feeds();
        	System.out.println("------------");
        	this.myBoard.displayBoard();
		}
	}

	private void deploy(WarPlayer player) {
		int randRow = (int) (1 + (this.myBoard.getWidth() - 1) * Math.random());
		int randColumn = (int) (1 + (this.myBoard.getLength() - 1) * Math.random());
		WarTile currentTile = (WarTile) this.myBoard.getTile(randRow, randColumn);
		Military currentMilitary = player.mission();

		WarTile northTile = this.myBoard.getNorthTile(randRow, randColumn);
		WarTile southTile = this.myBoard.getSouthTile(randRow, randColumn);
		WarTile eastTile = this.myBoard.getEastTile(randRow, randColumn);
		WarTile westTile = this.myBoard.getWestTile(randRow, randColumn);

		if (currentMilitary != null) {
			// North Tile
			if (northTile != null) {
				// allié
				try {
					if (!player.isAnEnemy(northTile.getMilitary())) {
						currentTile.applyAlliedEffect(northTile.getMilitary());
					} else {
						// Enemy
						int n = currentTile.applyEnemyEffect(northTile.getMilitary());
						if (n != 0) {
							player.captureEnemy(northTile.getMilitary().getNumberOfWarriors());
						}
					}
				} catch (NullPointerException e) {
					System.out.println("North tile adjacent " + northTile.toString() + " is not occupied");
				}

			}
			// south Tile
			if (southTile != null) {
				// allié
				try {
					if (!(player.isAnEnemy(southTile.getMilitary()))) {
						currentTile.applyAlliedEffect(southTile.getMilitary());
					} else {
						// Enemy
						int n = currentTile.applyEnemyEffect(southTile.getMilitary());
						if (n != 0) {
							player.captureEnemy(southTile.getMilitary().getNumberOfWarriors());
						}
					}
				} catch (NullPointerException e) {
					System.out.println("South tile adjacent " + southTile.toString() + " is not occupied");
				}

			}

			// East tile
			if (eastTile != null) {
				// allié
				try {
					if (!(player.isAnEnemy(eastTile.getMilitary()))) {
						currentTile.applyAlliedEffect(eastTile.getMilitary());
					} else {
						// Enemy
						int n = currentTile.applyEnemyEffect(eastTile.getMilitary());
						if (n != 0) {
							currentMilitary.gainGold(1);
							player.captureEnemy(eastTile.getMilitary().getNumberOfWarriors());
						}
					}

				} catch (NullPointerException e) {
					System.out.println("Est tile adjacent " + eastTile.toString() + " is not occupied");
				}

			}
			// West Tile
			if (westTile != null) {
				// allié
				try {
					if (!(player.isAnEnemy(westTile.getMilitary()))) {
						currentTile.applyAlliedEffect(westTile.getMilitary());
					} else {
						// Enemy
						int n = currentTile.applyEnemyEffect(westTile.getMilitary());
						System.out.println(n);
						if (n != 0) {
							player.captureEnemy(westTile.getMilitary().getNumberOfWarriors());
						}

					}
				} catch (NullPointerException e) {
					System.out.println("West tile adjescent " + westTile.toString() + " is not occupied");
				}

			}

			// deploie
			try {
				currentMilitary.deployed(currentTile);
				player.getMyTroops().add(currentMilitary);
				player.getMyTiles().add(currentTile);
				player.setNumberOfTerritory(1);
				System.out.println(player.toString() + " deploie une armée de " + currentMilitary.getNumberOfWarriors()
						+ " guerrier sur" + currentTile.toString() + "(" + randColumn + "," + randRow + ")");


			} catch (NoMoreWarriorsToDeployException e) {
				System.out.println("You cannot deploy a troop of " + currentMilitary.getNumberOfWarriors() + "!");
				System.out.println("You have " + player.getNumberOfWarriors() + " warrior(s) left ! ");
			} catch (OceanDeniedCharacterException e) {
				player.setNumberOfWarriors(currentMilitary.getNumberOfWarriors());
				System.out.println("Sorry, you cannot deploy an army in ocean");
			} catch (TileAlreadyOccupiedException e) {
				player.setNumberOfWarriors(currentMilitary.getNumberOfWarriors());
				System.out.println(e.getMessage()+" by "+currentTile.getMilitary().getMilitaryName());
			} catch (WarriorSizeException e) {
				player.setNumberOfWarriors(currentMilitary.getNumberOfWarriors());
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	/**
	 * Method is used to get the score of the player corresponding the given parameter
	 * @param index the indexed player
	 */
	public int getScorePlayer(int index) {
		int score = 0;
		score += this.players.get(index).getGold();
		int size = this.players.get(index).getMyTiles().size();
		for (int i = 0; i < this.players.get(index).getMyTroops().size(); i++) {
			score += this.players.get(index).getMyTroops().get(i).getGold();
		}
		WarTile tile = null;
		for (int i = 0; i < size; i++) {
			tile = this.players.get(index).getMyTiles().get(i);
			if (tile != null) {
				if (tile instanceof WarPlain)
					score += 1;
				else if (tile instanceof WarWoodland)
					score += 2;
				else if (tile instanceof WarDesert || tile instanceof WarMountain)
					score += 5;
			}
		}

		if (this.players.get(index).getNumberOfTerritory() >= 10)
			score += 5;
		return score;
	}

	/**
	 * Returns the list of players that have the maximum score
	 * @return the list of players that have the maximum score
	 */
	public List<WarPlayer> getWinner() {
		int max = this.getScorePlayer(0);
		for (int i = 0; i < this.getNbPlayer(); i++) {
			if (this.getScorePlayer(i) >= max) {
				max = this.getScorePlayer(i);
			}
		}
		// recherhe des joueurs ayant ce maximum
		List<WarPlayer> lst = new ArrayList<>();
		for (int i = 0; i < this.getNbPlayer(); i++) {
			if (this.getScorePlayer(i) == max) {
				lst.add(this.players.get(i));
			}
		}
		return lst;
	}

}
