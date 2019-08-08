package net.ddns.alexraspberry.trains.model;

import java.util.List;

import lombok.Getter;

@Getter
public class Result {

	private boolean success;
	private List<Route> routes;
	
	
	public int getNumberOfRoutes() {
		return routes.size();
	}
	
	public Route getBestRoute() {
		routes.sort(null);
		return routes.get(0);
	}
	
}
