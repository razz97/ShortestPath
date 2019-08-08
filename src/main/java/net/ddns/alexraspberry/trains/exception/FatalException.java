package net.ddns.alexraspberry.trains.exception;

import lombok.AllArgsConstructor;

/**
 * Represents an exception that prevents the program from working.
 * @author alex
 *
 */
@SuppressWarnings("serial")
@AllArgsConstructor
public class FatalException extends Exception {

	private Cause cause;
	
	/**
	 * Represents the different causes for a FatalException
	 * @author alex
	 *
	 */
	@AllArgsConstructor
	public enum Cause {
		FILES("Could not access or create data files."),
		DATA("Road data is either empty or corrupted.");
		
		private String message;
		
	}
	
	@Override
	public String getMessage() {
		return "There was a fatal error while loading data, cause: " + cause.message;
	}

	
	
}
