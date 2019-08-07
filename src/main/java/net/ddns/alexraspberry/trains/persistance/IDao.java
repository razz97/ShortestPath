package net.ddns.alexraspberry.trains.persistance;

import java.util.List;

import net.ddns.alexraspberry.trains.model.Road;
import net.ddns.alexraspberry.trains.model.Town;

public interface IDao {
	
	public List<Town> getTowns();

	public Town getTown(String name);
	
	public List<Road> getRoads();
	
}
