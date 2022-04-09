package game.gameTypes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import TypePlayers.FarmerPlayer;
import gameTypes.FarmerGame;

public class TestFarmersGame {
	FarmerPlayer p1 = new FarmerPlayer("YO");
	FarmerPlayer p2 = new FarmerPlayer("JO");
	

	@Test
	public void testScoreOfAPlayerBeforePlaying() {
		ArrayList<FarmerPlayer> lst = new ArrayList<>() ;
		lst.add(p1);
		lst.add(p2);
		FarmerGame game = new FarmerGame(10, 10, lst);
		assertEquals(15, game.getScorePlayer(0));
		assertEquals(15, game.getScorePlayer(1));

	}
	

	@Test
	public void testMethodWinnerWhenPlayerHasTheSameScore() {
		ArrayList<FarmerPlayer> lst = new ArrayList<>() ;
		lst.add(p1);
		lst.add(p2);
		FarmerGame game = new FarmerGame(10, 10, lst);
		assertEquals(15, game.getScorePlayer(0));
		assertEquals(15, game.getScorePlayer(1));
		
		assertEquals(lst, game.getWinner());

	}

}
