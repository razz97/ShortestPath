package net.ddns.alexraspberry.trains.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import net.ddns.alexraspberry.trains.model.Road;
import net.ddns.alexraspberry.trains.model.Town;

@RunWith(SpringRunner.class)
public class RoadUnitTests {

	@Test
	public void constructorTest() {
		Road road = new Road(5, new Town("A"), new Town("B"));
		assertEquals(5, road.getWeight());
		assertEquals(new Town("A"), road.getOrigin());
		assertEquals(new Town("B"), road.getDestination());
	}
	
}
