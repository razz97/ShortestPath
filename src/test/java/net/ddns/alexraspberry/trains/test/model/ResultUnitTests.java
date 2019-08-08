package net.ddns.alexraspberry.trains.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import net.ddns.alexraspberry.trains.model.Result;

@RunWith(SpringRunner.class)
public class ResultUnitTests {
	
	@Test
	public void constructorRouteSuccess() {
		
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
		
	}
	

}
