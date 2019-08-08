package net.ddns.alexraspberry.trains.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import net.ddns.alexraspberry.trains.model.Result;
import net.ddns.alexraspberry.trains.model.Road;
import net.ddns.alexraspberry.trains.model.Town;

@Component
public class QueryController {

	
	public Result resolveQuery(List<Town> route) {
		// List containing all posible paths	
		List<List<Town>> paths;
		// List containing a path while its being created
		List<Town> tmpRoute = new ArrayList<>();
		
		// Get all posible paths from the input route
		for (int i = 0; i < route.size() - 1; i++) {
			// Refresh the paths array each step
			paths = new ArrayList<>();
			// Compute paths from town index i to town index i + 1
			for (List<Town> towns : getPaths(route.get(i), route.get(i + 1), tmpRoute, paths)) {
				// Towns contains all posible paths
				System.out.print(route.get(i));
				towns.forEach(System.out::print);
				System.out.println("");
			}
			System.out.println("-----------------");
		}
		
		return null;
	}
	
	private List<List<Town>> getPaths(Town current, Town goal, List<Town> currentRoute, List<List<Town>> routes) {
		// Set the current town as visited
		current.setVisited(true);
		// If the current town is the goal, we finished a route!
		if (current.equals(goal)) {
			routes.add(new ArrayList<>(currentRoute));
			current.setVisited(false);
			return routes;
		}
		// For each road in the current town that's not visited... 
		for (Road r : current.getRoadsOut()) {
			if (!r.getDestination().isVisited()) {
				// Add its destination to the current route
				currentRoute.add(r.getDestination());
				// Call the function again for the destination of each not visited town
				getPaths(r.getDestination(), goal, currentRoute, routes);
				// Once it finishes, remove it, we finished a route!
				currentRoute.remove(r.getDestination());
			}
			
		}
		// Once it finishes, mark current town as not visited, we finished a route!
		current.setVisited(false);
		return routes;
	}
	
	
}
