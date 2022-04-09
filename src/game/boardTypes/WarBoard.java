package game.boardTypes;

import game.Board;
import game.Tile;
import game.exception.UnknownTileException;
import game.lands.WarTile;
import game.lands.WarLand.*;

/**
 * This class represents a war board game
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */ 

public class WarBoard extends Board {

	/**
	 * Creates an instance of this class with given width and length
	 * @param width this board's width
	 * @param length this board's length
	 */
    public WarBoard(int width, int length) { 
    	super(width, length);
    }
    
    @Override
    /**
     * This method is used to initialize this board
     * @param width the width of this board
     * @param length the length of this board
     */
    public void initBoard(int width, int length) 
    {
    	for(int i=0; i<width; i++) {
			for (int j=0; j< length; j++) 
			{
				this.tiles[i][j] = new WarOcean();
			}
		}
    	this.shuffleBoard();
    }

    @Override
    /**
     * It returns random tile
     * @return returns a tile
     */
    protected Tile randomTile()
    {
    	int index = Board.alea.nextInt(4);
    	WarTile t[] = {new WarDesert(), new WarMountain(), new WarPlain(), new WarWoodland()};
    	
    	return t[index];
    }
    
	@Override
	/**
	 * Method that helps to shuffle our board
	 */
    public void shuffleBoard()
    {
    
		int nbOthersL = (int)(this.getLength() * 2)/3;
    	int nbOthersR = (int)(this.getWidth() * 2)/3;
    	for (int row = 2 ; row < nbOthersL + 1 ; row++) 
    	{
            for (int column = 2 ; column < nbOthersR + 1 ; column++) 
            {
            	if( this.tiles[row][column] instanceof WarOcean ) {
            		this.tiles[row][column] = this.randomTile();
            	}
            		
            }            
        }  
    }
	
	
	/**
	 * Returns the tile to the north side of the current tile
	 * @param i the index of the north tile in the row board
	 * @param j the index of the north tile in the column board
	 * @return the tile to the north side of the current tile
	 */
	public WarTile getNorthTile(int i, int j ) {
    	WarTile t = null;
    	if ((i+1>=0 && i+1<= this.getWidth()) &&  (j>=0 &&j<= this.getLength())) {
    		try {
    			t= ((WarTile) this.getTile(i+1, j));
    		}catch(UnknownTileException e) {
    			System.out.println("No west Tile adjacent");
    		}
    	}
    	return t;
    }
    
	/**
	 * Returns the tile to the south side of the current tile
	 * @param i the index of the south tile in the row board
	 * @param j the index of the south tile in the column board
	 * @return the tile to the south side of the current tile
	 */
    public WarTile getSouthTile(int i, int j ) {
    	WarTile t = null;
    	if ((i-1>=0 && i-1<= this.getWidth()) &&  (j>=0 &&j<= this.getLength())) {
    		try {
    			t = ((WarTile)this.getTile(i-1, j));
    		}catch(UnknownTileException e) {
    			System.out.println("No south Tile adjacent");
    		}
        }
    	return t;
    }
    
    /**
	 * Returns the tile to the east side of the current tile
	 * @param i the index of the east tile in the row board
	 * @param j the index of the east tile in the column board
	 * @return the tile to the east side of the current tile
	 */
    public WarTile getEastTile(int i, int j ) {
    	WarTile t = null;
    	if ((i>=0 &&i<= this.getWidth()) &&  (j-1>=0 && (j-1)<= this.getLength())) {
    		try {
    			t = ((WarTile)this.getTile(i, j-1));
    		}catch(UnknownTileException e) {
    			System.out.println("No east Tile adjacent");
    		}
    		
    	}
    	return t;
    }
    
    /**
	 * Returns the tile to the west side of the current tile
	 * @param i the index of the west tile in the row board
	 * @param j the index of the west tile in the column board
	 * @return the tile to the west side of the current tile
	 */
    public WarTile getWestTile(int i, int j ) {
    	WarTile t = null;
    	if ((i>=0 && i<=this.getWidth()) &&  (j+1>=0 && j+1<= this.getLength())) {
    		try {
            	t = ((WarTile)this.getTile(i, j+1)) ;
    		}catch(UnknownTileException e) {
    			System.out.println("No west Tile adjacent");
    		}
    	}
    	return t;
    }
	
}
