package net.ddns.alexraspberry.trains.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import net.ddns.alexraspberry.trains.model.Road;
import net.ddns.alexraspberry.trains.model.Town;

@RunWith(SpringRunner.class)
public class RoadUnitTests {

	@Test
	public void constructorTest() {
		Town townA = new Town("A");
		Road road = new Road(5, townA, new Town("B"));
		assertNotNull(road);
		assertEquals(5, road.getWeight());
		assertEquals(townA, road.getOrigin());
		assertEquals(new Town("B"), road.getDestination());
		assertEquals(townA.getRoadsOut().get(0), road);
	}
}
