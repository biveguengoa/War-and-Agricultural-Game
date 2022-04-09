package game.characters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.exception.WarriorSizeException;
import game.lands.WarTile;
import game.lands.WarLand.*;

public class MilitaryTest {
	
	private Military charac1;
	
	@Before
	public void before() {
		this.charac1 = new Military(1, "Army1");
	}

	@Test
	public void testChangeTroopWhenOK() {
		// on verifie que l'armée a bien 1 guerrier au départ
		assertEquals(1, this.charac1.getNumberOfWarriors());
		// on change ensuite de troupe
		this.charac1.changeTroop(4);
		// on vérifie que la troupe a bien été changée
		assertEquals(4, this.charac1.getNumberOfWarriors());
	}
	
	@Test (expected = WarriorSizeException.class)
	public void changeTroopThrowsWarriorSizeException() throws WarriorSizeException {
		// on verifie que l'armée a bien 1 guerrier au départ
		assertEquals(1, this.charac1.getNumberOfWarriors());
		// on essaie de changer de troupe
		this.charac1.changeTroop(0);
	}
	
	@Test
	public void testGetPaid() {
		WarTile desert = new WarDesert(this.charac1);
		// on vérifie que l'armée a bien 0 pièce d'or au départ
		assertEquals(0, this.charac1.getGold());
		int gain = this.charac1.getPaid(desert);
		// on vérifie que l'armée a bien reçu le double de sa taille
		assertTrue(2 == gain);
		// on vérifie que l'or a bien été comptabilisé
		assertEquals(2, this.charac1.getGold());
	}

}
