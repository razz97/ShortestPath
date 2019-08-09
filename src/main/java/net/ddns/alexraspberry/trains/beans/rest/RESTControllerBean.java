package net.ddns.alexraspberry.trains.beans.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.ddns.alexraspberry.trains.beans.QuerySolverBean;
import net.ddns.alexraspberry.trains.exception.InvalidActionException;
import net.ddns.alexraspberry.trains.exception.InvalidActionException.Cause;
import net.ddns.alexraspberry.trains.model.Result;
import net.ddns.alexraspberry.trains.model.Town;
import net.ddns.alexraspberry.trains.persistance.IDao;

@Service @Path("/api") @Slf4j
@Produces(MediaType.APPLICATION_JSON)
public class RESTControllerBean {

	@Autowired
	private IDao dao;
	
	@Autowired
	private QuerySolverBean controller;
	
	@GET @Path("/towns")
	public List<Town> getAll() {
		log.info("Get request to /api/towns");
		return dao.getTowns();
	}
	
	@GET @Path("/route/{query}")
	public Result getResult(@PathParam("query") String query) {
		log.info("Get request to /api/route/" + query);
		String[] townNames = query.split("-");
		List<Town> towns = new ArrayList<>();
		try {
			if (townNames.length < 2)
				throw new InvalidActionException(Cause.INVALID_INPUT);
			for (String townName : townNames) 
				towns.add(dao.getTown(townName));
			return controller.resolveRoute(towns);
		} catch (InvalidActionException e) {
			return new Result(e.getMessage());
		}
	}
	
	@GET @Path("/shortest/{query}")
	public Result getShortestPathResult(@PathParam("query") String query) {
		log.info("Get request to /api/shortest/" + query);
		String[] townNames = query.split("-");
		try {
			if (townNames.length < 2)
				throw new InvalidActionException(Cause.INVALID_INPUT);
			return controller.resolveShortestPath(dao.getTown(townNames[0]), dao.getTown(townNames[1]));
		} catch (InvalidActionException e) {
			return new Result(e.getMessage());
		}
	}
	
}
