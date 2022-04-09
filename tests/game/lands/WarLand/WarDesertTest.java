package game.lands.WarLand;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import game.characters.Military;
import game.exception.TileAlreadyOccupiedException;
import game.exception.WarriorSizeException;

public class WarDesertTest {
	
	private WarDesert desert;
	
	@Before
	public void before() {
		this.desert = new WarDesert();
	}

	@Test
	public void testSetMilitaryWhenOK() {
		Military mlt = new Military(2, "Team1");
		assertFalse(this.desert.isOccupied());
		// on place l'armée sur la tuile
		this.desert.setMilitary(mlt);
		// on s'assure que l'armée a bien été positionnée
		assertTrue(this.desert.isOccupied());
		assertEquals(mlt, this.desert.getMilitary());
	}
	
	@Test(expected = TileAlreadyOccupiedException.class)
	public void setMilitaryThrowsTileAlreadyOccupiedException() throws WarriorSizeException, TileAlreadyOccupiedException {
		Military mlt = new Military(2, "Team1");
		Military mlt2 = new Military(2, "Team2");
		this.desert.setMilitary(mlt);
		assertEquals(mlt, this.desert.getMilitary());
		this.desert.setMilitary(mlt2);
	}
	
	@Test(expected = WarriorSizeException.class)
	public void setMilitaryThrowsWarriorSizeExceptionException() throws WarriorSizeException, TileAlreadyOccupiedException {
		Military mlt = new Military(5, "Escouade1");
		this.desert.setMilitary(mlt);
	}
	
	@Test
	public void testApplyEnemyEffect() {
		Military mlt1 = new Military(2, "Team1");
		Military mlt2 = new Military(3, "Team2");
		assertEquals(0, mlt1.getGold());
		this.desert.setMilitary(mlt2);
		int size = this.desert.applyEnemyEffect(mlt1);
		assertEquals(size, mlt1.getNumberOfWarriors());
		assertEquals(0, mlt2.getGold());
		
	}

}
