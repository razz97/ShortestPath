package net.ddns.alexraspberry.trains.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * Represents the result of a query about a route
 * @author alex
 *
 */
@Getter
public class Result {

	private boolean success;
	private int distance;
	private List<Town> route;
	private String errorMsg;
	private boolean error;
	
	
	/**
	 * Use this result when the query was a failure
	 */
	public Result(boolean success) {
		this.success = success;
		this.distance = 0;
		this.route = new ArrayList<>();
		this.error = false;
		this.errorMsg = "";
	}

	/**
	 * Use this constructor when the query was successful
	 */
	public Result(boolean success, int distance, List<Town> route) {
		this.success = success;
		this.distance = distance;
		this.route = route;
		this.error = false;
		this.errorMsg = "";
	}
	
	/**
	 * Only use this constructor when an error has ocurred
	 * @param errorMsg the description of the error
	 */
	public Result(String errorMsg) {
		this.error = true;
		this.errorMsg = errorMsg;
		this.success = false;
		this.distance = 0;
		this.route = new ArrayList<>();
	}
	
}
