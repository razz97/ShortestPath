package net.ddns.alexraspberry.trains.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ddns.alexraspberry.trains.model.Town;
import net.ddns.alexraspberry.trains.persistance.IDao;

@Service
@Path("/")
public class TownService {

	@Autowired
	private IDao dao;
	
	
	@GET
//	@Path("/{name}")
//	@Produces(MediaType.APPLICATION_JSON)
	public String getTown() {
//		return new Town(name);
		return "works!!";
	}
	
}
