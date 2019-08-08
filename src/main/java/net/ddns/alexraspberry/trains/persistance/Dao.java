package net.ddns.alexraspberry.trains.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.ddns.alexraspberry.trains.exception.FatalException;
import net.ddns.alexraspberry.trains.exception.FatalException.Cause;
import net.ddns.alexraspberry.trains.exception.InvalidActionException;
import net.ddns.alexraspberry.trains.model.Road;
import net.ddns.alexraspberry.trains.model.Town;

/**
 * Responsible for keeping and accesing data, and convert it into objects.
 * @author alex
 *
 */
@Repository
@Slf4j
public class Dao implements IDao {

	private File dataFolder;
	private File dataFile;
	
	private List<Town> towns;
	private List<Road> roads;
		
	@SneakyThrows
	@PostConstruct
	public void init() {
		towns = new ArrayList<>();
		roads = new ArrayList<>();
		// The following code may throw a fatal exception, if this happens, 
		// the program should not start (sneaky throws wraps it into a runtime exception)
		assertFilesExist();
		log.info("Data folder and routes.txt exist :)");
		loadDataIntoMemory();
		log.info("Data loaded into memory :");
		log.debug("DAO initialized correcltly.");
	}
	
	@Override
	public List<Town> getTowns() {
		return towns;
	}

	@Override
	public Town getTown(String name) throws InvalidActionException {
		return towns.stream()
				.filter(t -> t.getName().equals(name))
				.findAny()
				.orElseThrow(() -> new InvalidActionException(InvalidActionException.Cause.TOWN_NOT_FOUND));
	}
	
	/**
	 * Loads all roads and towns into memory
	 * @throws FatalException
	 */
	private void loadDataIntoMemory() throws FatalException {
		try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
			roads = br.lines()
					.map(str -> decodeRoad(str))
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
			if (roads.isEmpty())
				throw new FatalException(Cause.DATA);
		} catch (IOException e) {
			throw new FatalException(Cause.DATA);
		}
	}
	
	/**
	 * Makes sure all data files exist, creates them when they dont
	 * @throws FatalException if files could not be accessed or created
	 */
	private void assertFilesExist() throws FatalException  {
		dataFolder = new File("data");
		dataFile = new File("data" + File.separatorChar + "roads.txt");
		try {
			if (!dataFolder.exists() && !dataFolder.mkdirs())
				throw new FatalException(Cause.FILES);
			if (!dataFile.exists())
				dataFile.createNewFile();
		} catch (IOException e) {
			throw new FatalException(Cause.FILES);
		}
	}
	
	/**
	 * Maps an encoded road into an object road
	 */
	private Road decodeRoad(String road) {
		try {
			String cleanRoad = road.trim();
			Town from = getOrCreateTown(cleanRoad.substring(0, 1));
			int weight = Integer.parseInt(cleanRoad.substring(2, cleanRoad.length()));
			Town to = getOrCreateTown(cleanRoad.substring(1, 2));
			return new Road(weight, from, to);
		} catch (NumberFormatException e){
			// If a road weight is wrong we skip it and return null. The program can continue afterall.
			// Still good idea to log it into tomcat:
			log.error(new FatalException(Cause.DATA).getMessage());
		}
		// Nulls are filtered afterwards
		return null;
	}
	
	/**
	 * Get a town by its name, create it if it doesn't exist.
	 */
	private Town getOrCreateTown(String name) {
		Town tmpTown = new Town(name);
		int index = towns.indexOf(tmpTown);
		if (index == -1) {
			towns.add(tmpTown);
			return tmpTown;
		}
		return towns.get(index);
	}

	
}
