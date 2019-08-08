package net.ddns.alexraspberry.trains.beans.rest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ddns.alexraspberry.trains.beans.QueryController;
import net.ddns.alexraspberry.trains.model.Result;
import net.ddns.alexraspberry.trains.model.Town;
import net.ddns.alexraspberry.trains.persistance.IDao;

@Service
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class RESTController {

	@Autowired
	private IDao dao;
	
	@Autowired
	private QueryController controller;
	
	@GET
	@Path("/towns")
	public List<Town> getAll() {
		return dao.getTowns();
	}
	
	@GET
	@Path("/route/{query}")
	public Result getResult(@PathParam("query") String query) {
		List<Town> towns = Arrays.asList(query.trim().split("-"))
				.stream().map(s -> dao.getTown(s))
				.collect(Collectors.toList());
		
		return controller.resolveQuery(towns);
	}
	
	
}
