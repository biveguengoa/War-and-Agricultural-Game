package game.boardTypes;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Board;
import game.exception.UnknownTileException;

public class TestFarmerBoard {

	FarmersBoard board = new FarmersBoard(10, 10);
	
	@Test(expected = UnknownTileException.class)
	public void testGetTileWhenOutOfLength() {
		board.getTile(9, 10);
	}

}
