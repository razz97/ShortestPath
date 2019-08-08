package net.ddns.alexraspberry.trains.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import net.ddns.alexraspberry.trains.exception.InvalidActionException;
import net.ddns.alexraspberry.trains.model.Road;
import net.ddns.alexraspberry.trains.model.Town;

@RunWith(SpringRunner.class)
public class TownUnitTests {
	
	@Test
	public void constructor() {
		Town town = new Town("A");
		assertNotNull(town);
		assertEquals(town.getName(), "A");
		assertEquals(town.getDistanceToStart(), Integer.MAX_VALUE);
		assertNull(town.getPrevious());
		assertNotNull(town.getRoadsOut());
	}
	
	@Test
	public void distanceToTest() throws InvalidActionException {
		Town townA = new Town("A");
		Town townB = new Town("B");
		new Road(10, townA, townB);
		assertEquals(townA.distanceTo(townB), 10);
	}
	
	@Test
	public void isConnectedToTest() {
		Town townA = new Town("A");
		Town townB = new Town("B");
		new Road(10, townA, townB);
		assertTrue(townA.isConnectedTo(townB));
	}
	
	@Test
	public void comparableTest() {
		Town townA = new Town("A");
		townA.setDistanceToStart(0);
		Town townB = new Town("B");
		townB.setDistanceToStart(10);
		assertTrue(townA.compareTo(townB) < 0);
	}

}
