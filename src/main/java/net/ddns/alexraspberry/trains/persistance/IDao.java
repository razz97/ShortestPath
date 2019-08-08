package net.ddns.alexraspberry.trains.persistance;

import java.util.List;

import net.ddns.alexraspberry.trains.exception.InvalidActionException;
import net.ddns.alexraspberry.trains.model.Town;

/**
 * Supplier of objects that are read from the data files.
 * @author alex
 *
 */
public interface IDao {
	
	/**
	 * Gets all towns
	 */
	public List<Town> getTowns();

	/**
	 * Gets a town given its name.
	 * @throws InvalidActionException in case the town doesnt exist
	 */
	public Town getTown(String name) throws InvalidActionException;
	
		
}
