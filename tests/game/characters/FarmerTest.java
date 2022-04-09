package game.characters;


import static org.junit.Assert.*;

import org.junit.Test;

import game.lands.FarmersTile;
import game.lands.FarmersLand.FarmerDesert;
import game.lands.FarmersLand.FarmerMountain;
import game.lands.FarmersLand.FarmerPlain;

public class FarmerTest {

	Farmer farmer1 = new Farmer();
	Farmer farmer2 = new Farmer();
	Farmer farmer3 = new Farmer();
	
	
	@Test
	public void testWhenDeployingAMilitary() {
		// Initialize a plain tile
		FarmersTile plain = new FarmerPlain();
		// Make sure the plain tile has no farmer
		assertNull(plain.getFarmer());
		//deploy a farmer in the plain tile
		farmer1.deployed(plain);
		//Make sure the tile is occupied
		assertTrue(plain.isOccupied());
	}
	
	@Test
	public void testWhenMilitaryGotPaid() {
		// Initialize some tiles
		FarmersTile plain = new FarmerPlain();
		FarmersTile desert = new FarmerDesert();
		FarmersTile mountain = new FarmerMountain();

		// Make sure these tiles has no farmers
		assertNull(plain.getFarmer());
		assertNull(desert.getFarmer());
		assertNull(mountain.getFarmer());
		
		//deploy a farmer in these tiles
		farmer1.deployed(plain);
		farmer2.deployed(desert);
		farmer3.deployed(mountain);

		//Make sure the tiles are occupied
		assertTrue(plain.isOccupied());
		assertTrue(desert.isOccupied());
		assertTrue(mountain.isOccupied());

		//Let's pay them
		int paiment1 = farmer1.getPaid(plain);
		int paiment2 = farmer2.getPaid(desert);
		int paiment3 = farmer3.getPaid(mountain);

		//Make sure they really get paid
		assertEquals(1, paiment1);
		assertEquals(3, paiment2);
		assertEquals(5, paiment3);
		
		
		// Just to really make sure we get them right
		assertNotEquals(5, paiment1);
		assertNotEquals(1, paiment2);
		assertNotEquals(3, paiment3);

	}
	
	@Test
	public void testWhenCollectingResource() {
		FarmersTile plain = new FarmerPlain();
		//deploy farmer1 in the plain tile
		farmer1.deployed(plain);
		//Let's collect some resource
		int res = farmer1.collectResource(plain);
		// Make sure we collect 1 resource
		assertEquals(1, res);
	}
}
