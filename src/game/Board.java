package game;

import java.util.Random;

import game.exception.UnknownTileException;

/**
 * This class represents a board game
 * @author BIVEGUE Isabelle, BENIDER Imad, KEBE Moustapha and AICI Halima
 * @version 1.0
 */

public abstract class Board {
	
	protected Tile[][] tiles;
	protected int length;
	protected int width;
	protected static Random alea = new Random();
    
	/**
	 * Constructor that creates a board with given width and length
	 * @param width this board's width
	 * @param lenght this board's length
	 */
    public Board(int width, int length) { 
    	this.length = length;
    	this.width = width;
        this.tiles = new Tile[this.width][this.length];
        initBoard(this.width, this.length);
    }
    
    /**
     * This method is used to initialize this board
     * @param width the width of this board
     * @param length the length of this board
     */
    public abstract void initBoard(int width, int length) ;
    
    /**
     * It returns a random tile
     * @return returns a tile
     */
    protected abstract Tile randomTile();
    

    /**
	 * Provides this board's length
	 * @return this board's length
	 */
	public int getLength() {
		return this.length;
	}
	
	/**
	 * Provides this board's width
	 * @return the board's width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Method that helps to shuffle our board
	 */
    public abstract void shuffleBoard();
    
    /**
     * Test if all tiles are occupied except the ocean tile which can't take any character
     * @return <code>true</code> if all tiles are occupied except the ocean tile which can't take any character
     */
    public boolean allOccupied() {
    
    	int nbTileOccupied = 0;
        for(int i=0; i<this.width; i++) {
        	for(int j=0; j<this.length; j++) {
        		if(this.isOccupied(i,j)){
        			nbTileOccupied++;
        	}
        }
         
        int nbOthersL = (int)(this.getLength() * 2)/3;
    	int nbOthersR = (int)(this.getWidth() * 2)/3;
    	
        if(nbTileOccupied != nbOthersL * nbOthersR) 
        	return false;
    }
	return true;
 }
    
    /**
     * Enables to know whether or not there is a character on this board's tile at position 
     * (w, l) 
     * @param w represents the horizontal
     * @param l represents the vertical
     * @return <code>true</code> if the given indexes corresponding to that tile is occupied
     */
    public boolean isOccupied(int w, int l){
   
    	try {
    		if(this.getTile(w, l).isOccupied()) {
    			return true;
    		}
    	}catch(UnknownTileException e) {
    		System.out.println("coordinate doesn't exist!");
    	}
    	return false;
    }

    /**
	 * Provides this board's tile at position (i,j) if there is one
	 * @param i represents the horizontal 
	 * @param j represents the vertical
	 * @return tile at coordinates (i, j) on this board
	 * @throws UnknownTileException if tile at coordinates (i, j) does not exist
	 */
    public Tile getTile(int i, int j) throws UnknownTileException {
        
        if ((i>=0 && i<this.getWidth())  && ( j>=0 && j< this.getLength()))
            return this.tiles[i][j];
        else
        	throw new UnknownTileException("coordinate doesn't exist!");

    }
	

	/**
	 * Display the board on the screen
	 */
    public void displayBoard() {
         System.out.println();
        for(int i = 0; i < this.getWidth(); i++){
        	for( int j = 0; j < this.getLength(); j++) {
        		System.out.print(" | "+this.getTile(i,j).toString());
        	}
        	System.out.println(" | ");
        }
        System.out.println();
    }

}
