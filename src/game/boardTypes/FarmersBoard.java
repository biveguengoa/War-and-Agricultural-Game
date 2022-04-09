package game.boardTypes;

import game.Board;
import game.Tile;
import game.lands.FarmersTile;
import game.lands.FarmersLand.*;

/**
 * This class represents a farmer board game
 * 
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */

public class FarmersBoard extends Board {

	/**
	 * Creates a board of given <code>width</code> and <code>length</code>
	 * @param width this board's width
	 * @param length this board's length
	 */
	public FarmersBoard(int width, int length) {
		super(width, length);
	}

	@Override
	/**
	 * This method is used to initialize this board
	 * 
	 * @param width  the width of this board
	 * @param length the length of this board
	 */
	public void initBoard(int width, int length) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				this.tiles[i][j] = new FarmerOcean();
			}
		}
		this.shuffleBoard();
	}

	@Override
	/**
	 * It returns random tile
	 * 
	 * @return returns a tile
	 */
	protected Tile randomTile() {
		int index = Board.alea.nextInt(4);
		FarmersTile t[] = { new FarmerDesert(), new FarmerMountain(), new FarmerPlain(), new FarmerWoodland() };

		return t[index];
	}

	@Override
	/**
	 * Method that helps to shuffle our board
	 */
	public void shuffleBoard() {
		int nbOthersL = (int) (this.getLength() * 2) / 3;
		int nbOthersR = (int) (this.getWidth() * 2) / 3;
		for (int row = 2; row < nbOthersL + 1; row++) {
			for (int column = 2; column < nbOthersR + 1; column++) {
				if (this.tiles[row][column] instanceof FarmerOcean) {
					this.tiles[row][column] = this.randomTile();
				}

			}
		}
	}

}
