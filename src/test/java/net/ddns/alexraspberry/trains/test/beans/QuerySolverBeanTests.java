package net.ddns.alexraspberry.trains.test.beans;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.ddns.alexraspberry.trains.beans.QuerySolverBean;
import net.ddns.alexraspberry.trains.exception.InvalidActionException;
import net.ddns.alexraspberry.trains.model.Result;
import net.ddns.alexraspberry.trains.model.Town;
import net.ddns.alexraspberry.trains.persistance.IDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuerySolverBeanTests {
	
	@Autowired
	private QuerySolverBean bean;

	@Autowired
	private IDao dao;
	
	@Test
	public void resolveRouteTest1() throws InvalidActionException {
		List<Town> route = new ArrayList<>();
		route.add(dao.getTown("A"));
		route.add(dao.getTown("B"));
		route.add(dao.getTown("C"));
		Result result = bean.resolveRoute(route);
		assertEquals(result.getDistance(), 9);
	}
	
	@Test
	public void resolveRouteTest2() throws InvalidActionException {
		List<Town> route = new ArrayList<>();
		route.add(dao.getTown("A"));
		route.add(dao.getTown("D"));
		Result result = bean.resolveRoute(route);
		assertEquals(result.getDistance(), 5);
	}
	
	@Test
	public void resolveRouteTest3() throws InvalidActionException {
		List<Town> route = new ArrayList<>();
		route.add(dao.getTown("A"));
		route.add(dao.getTown("D"));
		route.add(dao.getTown("C"));
		Result result = bean.resolveRoute(route);
		assertEquals(result.getDistance(), 13);
	}
	
	@Test
	public void resolveRouteTest4() throws InvalidActionException {
		List<Town> route = new ArrayList<>();
		route.add(dao.getTown("A"));
		route.add(dao.getTown("E"));
		route.add(dao.getTown("B"));
		route.add(dao.getTown("C"));
		route.add(dao.getTown("D"));
		Result result = bean.resolveRoute(route);
		assertEquals(result.getDistance(), 22);
	}
	
	@Test
	public void resolveRouteTest5() throws InvalidActionException {
		List<Town> route = new ArrayList<>();
		route.add(dao.getTown("A"));
		route.add(dao.getTown("E"));
		route.add(dao.getTown("D"));
		Result result = bean.resolveRoute(route);
		assertFalse(result.isSuccess());
	}
	
	
	@Test
	public void resolveShortestPathTest() throws InvalidActionException {
		Result result = bean.resolveShortestPath(dao.getTown("A"), dao.getTown("C"));
		List<Town> rightRouteResult = new ArrayList<>();
		rightRouteResult.add(dao.getTown("A"));
		rightRouteResult.add(dao.getTown("B"));
		rightRouteResult.add(dao.getTown("C"));
		assertEquals(result.getDistance(), 9);
		assertArrayEquals(rightRouteResult.toArray(), result.getRoute().toArray());
	}
	
	
}
