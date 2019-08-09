package net.ddns.alexraspberry.trains.test.beans;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import net.ddns.alexraspberry.trains.exception.InvalidActionException;
import net.ddns.alexraspberry.trains.model.Result;
import net.ddns.alexraspberry.trains.model.Town;
import net.ddns.alexraspberry.trains.persistance.IDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RESTEndpointsTests {

	// TODO e2e fix errors
	
    @Autowired
    private IDao dao;
    
    @Test
    public void routeEndpointTest() {
//    	given().basePath("/clothes").get("").then().statusCode(200);
//    	TestRestTemplate restTemplate = new TestRestTemplate();
//        ResponseEntity<Result> entity = restTemplate.getForEntity("http://localhost/api/route/A-B-C", Result.class);
//        assertEquals(entity.getStatusCode(), HttpStatus.OK);
//        assertEquals(entity.getBody().getDistance(), 9);
    }
    
    @Test
    public void shortestEndpointTest() throws InvalidActionException {
//    	TestRestTemplate restTemplate = new TestRestTemplate();
//        ResponseEntity<Result> entity = restTemplate.getForEntity("http://localhost/api/shortest/A-C",Result.class);
//        assertEquals(entity.getStatusCode(), HttpStatus.OK);
//		List<Town> rightRouteResult = new ArrayList<>();
//		rightRouteResult.add(dao.getTown("A"));
//		rightRouteResult.add(dao.getTown("B"));
//		rightRouteResult.add(dao.getTown("C"));
//		assertEquals(entity.getBody().getDistance(), 9);
//		assertArrayEquals(rightRouteResult.toArray(), entity.getBody().getRoute().toArray());
    }
}
