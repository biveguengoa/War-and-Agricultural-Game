package game.lands.FarmersLand;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import game.characters.Farmer;
import game.exception.OceanDeniedCharacterException;
import game.exception.TileAlreadyOccupied;
import game.exception.TileAlreadyOccupiedException;
import game.lands.FarmersTile;

public class TestFarmerLand {

	Farmer farmer1 = new Farmer();
	Farmer farmer2 = new Farmer();
	FarmersTile plain = new FarmerPlain();

	@Test
	public void testSetFarmerWithoutException() {
		// Make sure the plain tile has no farmer
		assertNull(plain.getFarmer());
		//deploy a a farmer
		plain.setFarmer(farmer1);
		//Make sure the tile has a farmer deployed
		assertTrue(plain.isOccupied());
	}

	@Test(expected = TileAlreadyOccupied.class )
	public void testSetMilitaryWithException() {
		//Make sure the tile is not occupied
		assertFalse(plain.isOccupied());

		//Try to deploy two farmers
		plain.setFarmer(farmer1);
		plain.setFarmer(farmer2);
		
		// Make sure the tile is occupied
		assertTrue(plain.isOccupied());
		// Verify if we really get farmer1 in this tile
		assertSame(plain.getFarmer(), farmer1);
		//verify that the tile doesn't accept the farmer2
		assertNotSame(plain.getFarmer(), farmer2);
	}
	
	@Test(expected = OceanDeniedCharacterException.class )
	public void testSetMilitaryWithOceanTileException() {
		FarmerOcean ocean = new FarmerOcean();
		//Make sure the tile is not occupied
		assertFalse(ocean.isOccupied());

		//Try to deploy a farmer
		ocean.setFarmer(farmer2);
	
		// Make sure the tile is not occupied
		assertEquals(null, ocean.getFarmer());
		
	}
}
