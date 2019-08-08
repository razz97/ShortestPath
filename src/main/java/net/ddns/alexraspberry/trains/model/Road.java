package net.ddns.alexraspberry.trains.model;

import lombok.Getter;

/**
 * Represents a road, which essentially is an edge of a graph
 * @author alex
 *
 */
@Getter
public class Road {

	private int weight;
	private Town origin;
	private Town destination;
	
	public Road(int weight, Town origin, Town destination) {
		origin.getRoadsOut().add(this);
		this.weight = weight;
		this.origin = origin;
		this.destination = destination;
	}	
	
}
