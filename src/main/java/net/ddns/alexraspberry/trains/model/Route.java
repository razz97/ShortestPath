package net.ddns.alexraspberry.trains.model;

import java.util.List;

import lombok.Getter;

@Getter
public class Route implements Comparable<Route> {

	private List<Road> roads;
	
	public Route(List<Town> towns) {
		//TODO implement
	}
	
	public int getTotalDistance() {
		return roads.stream()
				.mapToInt(r -> r.getWeight())
				.sum();
	}

	@Override
	public int compareTo(Route o) {
		return getTotalDistance() - o.getTotalDistance();
	}
	
	
}
