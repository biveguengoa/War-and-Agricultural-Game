package game.TypePlayers;

import static org.junit.Assert.*;

import org.junit.Test;

import TypePlayers.FarmerPlayer;
import game.characters.Farmer;
import game.lands.FarmersTile;
import game.lands.FarmersLand.FarmerDesert;
import game.lands.FarmersLand.FarmerMountain;
import game.lands.FarmersLand.FarmerPlain;
import game.lands.FarmersLand.FarmerWoodland;

public class TestFarmerPlayer {

	FarmerPlayer player = new FarmerPlayer("YO");
	FarmersTile desert = new FarmerDesert();
	FarmersTile mountain = new FarmerMountain();
	FarmersTile plain = new FarmerPlain();
	FarmersTile woodland = new FarmerWoodland();


	
	@Test
	public void testWhenGettingResource() {
		// Make sure the desert tile has no farmer
		assertNull(desert.getFarmer());
				
		//deploy a farmer in the desert tile
		player.mission().deployed(desert);
		
		//Let's add the tile in the player's list of tiles
		player.addTile(desert);
	
		
		//Make sure the tile is occupied
		assertTrue(desert.isOccupied());
		
		// Let's get resource		
		assertEquals(1, player.getResource());
		// Just in case 
		assertNotEquals(0, player.getResource());

	}
	
	@Test
	public void testWhenPlayerMakePayment() {
		//Verifying that these tiles are not occupied
		assertFalse(desert.isOccupied());
		assertFalse(mountain.isOccupied());
		assertFalse(plain.isOccupied());
		assertFalse(woodland.isOccupied());
		
		// Let's deploy some farmers into those tiles
		Farmer farmer1 = (Farmer) player.mission() ;
		farmer1.deployed(desert);
		Farmer farmer2 = (Farmer) player.mission() ;
		farmer2.deployed(mountain);
		Farmer farmer3 = (Farmer) player.mission() ;
		farmer3.deployed(plain);
		Farmer farmer4 = (Farmer) player.mission() ;
		farmer4.deployed(woodland);
		
		// Let's add the tiles in the list of the player's tiles
		player.addTile(desert);
		player.addTile(mountain);
		player.addTile(plain);
		player.addTile(woodland);
		
		//Make sure the player has 15 golds
		assertEquals(15, player.getGold());
		// Let's pay them
		player.pay();

		//Let's get the payment of each and one of them
		int paiment1 = farmer1.getPaid(desert);
		int paiment2 = farmer2.getPaid(mountain);
		int paiment3 = farmer3.getPaid(plain);
		int paiment4 = farmer4.getPaid(woodland);

		//Make sure they really get paid
		assertEquals(3, paiment1);
		assertEquals(5, paiment2);
		assertEquals(1, paiment3);
		assertEquals(1, paiment4);
		
		//Make sure the player has less than 15 golds -->5 golds left
		assertEquals(5, player.getGold());
		assertNotEquals(15, player.getGold());
	
	}
	
	
	
	@Test
	public void testWhenPlayerExchangeRessources() {
		//Verifying that these tiles are not occupied
		assertFalse(desert.isOccupied());
		assertFalse(mountain.isOccupied());
		assertFalse(plain.isOccupied());
		assertFalse(woodland.isOccupied());
		
		// Let's deploy some farmers into those tiles
		Farmer farmer1 = (Farmer) player.mission() ;
		farmer1.deployed(desert);
		Farmer farmer2 = (Farmer) player.mission() ;
		farmer2.deployed(mountain);
		Farmer farmer3 = (Farmer) player.mission() ;
		farmer3.deployed(plain);
		Farmer farmer4 = (Farmer) player.mission() ;
		farmer4.deployed(woodland);
		
		
		// Let's add the tiles in the list of the player's tiles
		player.addTile(desert);
		player.addTile(mountain);
		player.addTile(plain);
		player.addTile(woodland);
		
		
		//Make sure player has 4 resources
		assertEquals(4, player.getResource());
		
		//Make sure the player has 15 golds
		assertEquals(15, player.getGold());
		
		// Let's exchange resources into gold
		player.exchangeRessources();


		//Make sure the player has more than 15 golds -->expected 32 golds after exchange
		assertEquals(32, player.getGold());
		assertNotEquals(15, player.getGold());
	
	}
	
	

	@Test
	public void testWhenPlayerPassHisTurn() {
		//Verifying that these tiles are not occupied
		assertFalse(desert.isOccupied());
		assertFalse(mountain.isOccupied());
		assertFalse(plain.isOccupied());
		assertFalse(woodland.isOccupied());
		
		// Let's deploy some farmers into those tiles
		Farmer farmer1 = (Farmer) player.mission() ;
		farmer1.deployed(desert);
		Farmer farmer2 = (Farmer) player.mission() ;
		farmer2.deployed(mountain);
		Farmer farmer3 = (Farmer) player.mission() ;
		farmer3.deployed(plain);
		Farmer farmer4 = (Farmer) player.mission() ;
		farmer4.deployed(woodland);
		
		
		// Let's add the tiles in the list of the player's tiles
		player.addTile(desert);
		player.addTile(mountain);
		player.addTile(plain);
		player.addTile(woodland);
		
		//Let's pass his turn so he can collect resources and wins 1 gold for a plain and woodland occupied and 2 for a desert land
		player.pass();
		
		// Let's get resource		
		assertEquals(4, player.getResource());
		//Make sure player has 4 golds + 15 golds =>19 golds
		assertEquals(19, player.getGold());
	}
}
