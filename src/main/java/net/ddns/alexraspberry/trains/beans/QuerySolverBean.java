package net.ddns.alexraspberry.trains.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.ddns.alexraspberry.trains.exception.InvalidActionException;
import net.ddns.alexraspberry.trains.model.Result;
import net.ddns.alexraspberry.trains.model.Road;
import net.ddns.alexraspberry.trains.model.Town;
import net.ddns.alexraspberry.trains.persistance.IDao;

/**
 * Bean responsible for solving queries related to routes and paths of the graph
 * @author alex
 *
 */
@Component
@Slf4j
public class QuerySolverBean {
	
	@Autowired
	private IDao dao;

	/**
	 * Given a list of towns, computes the distance, if there is no such route,
	 * return a failure result.
	 * @param route
	 * @return Result
	 */
	public Result resolveRoute(List<Town> route) {
		int distance = 0;
		// For each town except last...
		for (int i = 0; i < route.size() - 1; i++) {
			// Check if the next one is connected, if so add the distance of the road, else no such route exists
			Town current = route.get(i);
			Town next = route.get(i + 1);
			if (!current.isConnectedTo(next)) {
				log.info("Route resolved: failure");
				return new Result(false);
			}
			try { distance += current.distanceTo(next); }
			catch (InvalidActionException e) {/*This will never happen: we checked whether towns are connected*/}
		}
		log.info("Route resolved: success");
		return new Result(true, distance, route);
	}

	/**
	 * Given two towns, computes the shortest path between them and the distance it takes.
	 * @param origin
	 * @param destination
	 * @return
	 */
	public Result resolveShortestPath(Town origin, Town destination) {
		// FIXME: This function does collateral damage, it changes all towns in memory.
		// Probably wont work with concurrent requests to the api.
		computePaths(origin);
		// Once path is computed, generate the ordered list
        List<Town> path = new ArrayList<Town>();
        for (Town town = destination; town != null; town = town.getPrevious())
            path.add(town);
        Collections.reverse(path);
        Result result;
        // If the algorithm couldn't find a way to reach destination, then the distance to start
        // will still be infinite so no such route will exist
        if (destination.getDistanceToStart() < Integer.MAX_VALUE) {
        	log.info("Shortest path resolved: success");
        	result = new Result(true, destination.getDistanceToStart(), path);
        } else  {
        	log.info("Shortest path resolved: failure");
        	result = new Result(false);
        }
        //Reset all towns to initial conditions
        dao.getTowns().forEach(t -> {
        	t.setDistanceToStart(Integer.MAX_VALUE);
        	t.setPrevious(null);
        });
        return result;
	}

	/**
	 * Given a town, computes the shortest paths to all other towns, the result is
	 * written in the actual Town objects (Dao) through the previous property and distanceToStart.
	 * This is an implementation of Dijkstra's shortest path algorithm.
	 * @param origin
	 */
	private void computePaths(Town origin) {
		// Set the distance of origin to 0, (initial value is infinite)
		origin.setDistanceToStart(0);
		// This priority queue will allow us to automatically poll the town with the lowest distance to the origin, 
		// this is because Town implements comparable using the distances.
		PriorityQueue<Town> townQueue = new PriorityQueue<Town>();
		townQueue.add(origin);
		// While queue not empty...
		while (!townQueue.isEmpty()) {
			// Poll the lowest distance town from start.
			Town current = townQueue.poll();
			// For each road out...
			for (Road road : current.getRoadsOut()) {
				// Calculate the distance as if we where using the road
				Town destination = road.getDestination();
				int distanceThroughDestination = current.getDistanceToStart() + road.getWeight();
				// If its lower than its value then update its distance and previous node
				if (distanceThroughDestination < destination.getDistanceToStart()) {
					townQueue.remove(destination);
					destination.setDistanceToStart(distanceThroughDestination);
					destination.setPrevious(current);
					townQueue.add(destination);
				}
			}
		}
	}
}
