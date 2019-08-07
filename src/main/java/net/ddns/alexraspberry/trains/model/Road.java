package net.ddns.alexraspberry.trains.model;

import lombok.Getter;

@Getter
public class Road {

	private int weight;
	private Town origin;
	private Town destination;
	
	public Road(int weight, Town origin, Town destination) {
		origin.add(this);
		this.weight = weight;
		this.origin = origin;
		this.destination = destination;
	}
	
	public boolean isDestination(Town town) {
		return destination.equals(town);
	}
}
