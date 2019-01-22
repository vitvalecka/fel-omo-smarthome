package cz.housebrains.model.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PetTest {

	/**
	 * Check animal hungry and feed it
	 */
	@Test
	public void testHungry() {
		Pet cat = new Pet("cat", "Garfield", new Room("LivingRoom", 1));
		cat.isHungry();
		assertTrue(cat.hungry);
		cat.feed();
		assertFalse(cat.hungry);
		cat.nextRound();
	}

	/**
	 * Add the observer and notify
	 */
	@Test
	public void testObserver() {
		Room livingRoom = new Room("LivingRoom", 1);
		Pet cat = new Pet("cat", "Garfield", livingRoom);
		Person person = new Person("John", "male", 21, livingRoom);
		cat.addOvserver(person);
		cat.notifyAllObservers();
	}
}
