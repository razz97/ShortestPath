package net.ddns.alexraspberry.trains.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import net.ddns.alexraspberry.trains.model.Result;
import net.ddns.alexraspberry.trains.model.Town;

@RunWith(SpringRunner.class)
public class ResultUnitTests {
	
	@Test
	public void constructorRouteSuccess() {
		Result result = new Result(true, 30, new ArrayList<>(Arrays.asList(new Town("A"))));
		assertNotNull(result);
		assertNotNull(result.getRoute());
		assertNotNull(result.getRoute().get(0)); 
		assertNotNull(result.getErrorMsg());
		assertEquals(result.getDistance(), 30);
		assertEquals(result.isError(), false);
		assertEquals(result.isSuccess(), true);
	}
	
	@Test
	public void constructorRouteFailure() {
		Result result = new Result(false);
		assertNotNull(result);
		assertNotNull(result.getRoute());
		assertNotNull(result.getErrorMsg());
		assertEquals(result.getDistance(), 0);
		assertEquals(result.isError(), false);
		assertEquals(result.isSuccess(), false);
	}
	
	@Test
	public void constructorRouteError() {
		Result result = new Result("ERROR");
		assertNotNull(result);
		assertNotNull(result.getRoute());
		assertNotNull(result.getErrorMsg());
		assertEquals(result.getErrorMsg(), "ERROR");
		assertEquals(result.getDistance(), 0);
		assertEquals(result.isError(), true);
		assertEquals(result.isSuccess(), false);
	}
	

}
