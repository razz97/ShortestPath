package net.ddns.alexraspberry.trains.model;

import java.util.List;

import lombok.Getter;

@Getter
public class Result {

	private boolean success;
	private int distance;
	private List<Road> roads;
	
	
}
