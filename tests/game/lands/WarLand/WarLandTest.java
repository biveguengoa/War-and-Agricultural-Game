package game.lands.WarLand;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import game.lands.WarTile;
import game.characters.Military;
import game.exception.*;

public class WarLandTest {
	
	private WarTile mountain;
	private WarTile plain;
	private WarTile ocean;
	private WarTile woodland;
	
	@Before
	public void before() {
		this.mountain = new WarMountain();
		this.plain = new WarPlain();
		this.ocean = new WarOcean();
		this.woodland = new WarWoodland();
	}
	

	@Test
	public void testSetMilitaryWhenOK() {
		Military mlt1 = new Military(4, "Army1");
		Military mlt2 = new Military(5, "Army2");
		Military mlt3 = new Military(2, "Army3");
		
		// on vérifie que les tuiles sont inoccpuées au départ
		assertFalse(this.mountain.isOccupied());
		assertFalse(this.plain.isOccupied());
		assertFalse(this.woodland.isOccupied());
		assertFalse(this.ocean.isOccupied());
		
		// on place ensuite les armées sur les tuiles pouvant être occupées
		this.mountain.setMilitary(mlt3);
		this.plain.setMilitary(mlt2);
		this.woodland.setMilitary(mlt1);
		
		// on s'assure que les tuiles sont maintenant occupées
		assertTrue(this.mountain.isOccupied());
		assertTrue(this.plain.isOccupied());
		assertTrue(this.woodland.isOccupied());
	}
	
	@Test(expected = TileAlreadyOccupiedException.class)
	public void setMilitaryThrowsTileAlreadyOccupiedException() throws TileAlreadyOccupiedException {
		Military mlt = new Military(1, "Army1");
		Military mlt2 = new Military(5, "Army2");
		this.plain.setMilitary(mlt2);
		this.mountain.setMilitary(mlt);
		assertEquals(mlt2, this.plain.getMilitary());
		// on essaie de placer une autre armée sur la même tuile
		this.plain.setMilitary(mlt);
		this.mountain.setMilitary(mlt2);
		
		assertEquals(mlt2, this.plain.getMilitary());
		assertEquals(mlt, this.mountain.getMilitary());
	}
	
	@Test(expected = WarriorSizeException.class)
	public void setMilitaryThrowsWarriorSizeException() throws WarriorSizeException {
		Military army = new Military(5, "Troop");
		this.mountain.setMilitary(army);
	}
	
	@Test(expected = OceanDeniedCharacterException.class)
	public void setMilitaryThrowsOceanDeniedCharacterException() throws OceanDeniedCharacterException {
		Military army = new Military(5, "Troop");
		this.ocean.setMilitary(army);
		assertNull(this.ocean.getMilitary());
	}
	
	@Test
	public void testApplyEnemyEffect() {
		Military army1 = new Military(4, "army1");
		Military army2 = new Military(2, "army2");
		assertEquals(0, army1.getGold());
		this.woodland.setMilitary(army1);
		int size = this.woodland.applyEnemyEffect(army2);
		assertEquals(size, army2.getNumberOfWarriors());
		assertEquals(2, army1.getGold());
	}
	

}
