package net.ddns.alexraspberry.trains.exception;

import lombok.AllArgsConstructor;

/**
 * Represents an exception that allows the program to continue working.
 * @author alex
 *
 */
@SuppressWarnings("serial")
@AllArgsConstructor
public class InvalidActionException extends Exception {

	private Cause cause;
	
	/**
	 * Represents the different causes for a InvalidActionException
	 * @author alex
	 *
	 */
	@AllArgsConstructor
	public enum Cause {
		TOWN_NOT_FOUND("Town name is invalid."),
		TOWNS_NOT_CONNECTED("Towns are not connected.");
		
		private String message;
		
	}
	
	@Override
	public String getMessage() {
		return "There was an error while processing a request, cause: " + cause.message;
	}

	
	
}
