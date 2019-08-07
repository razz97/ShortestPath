package net.ddns.alexraspberry.trains.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import net.ddns.alexraspberry.trains.model.Road;
import net.ddns.alexraspberry.trains.model.Town;

@Repository
public class Dao implements IDao {

	private File dataFolder;
	private File dataFile;
	
	private List<Town> towns;
	private List<Road> roads;
	
	@PostConstruct
	public void init() throws IOException {
		//assertFilesExist();
		//loadDataIntoMemory();
		
		towns = new ArrayList<>();
		roads = new ArrayList<>();
		
		towns.add(new Town("A"));
	}
	
	@Override
	public List<Town> getTowns() {
		return towns;
	}

	@Override
	public Town getTown(String name) {
		return towns.stream().filter(t -> t.getName().equals(name)).findAny().orElse(null);
	}

	@Override
	public List<Road> getRoads() {
		return roads;
	}
	
	private void loadDataIntoMemory() {
		try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
			roads = br.lines()
					.map(str -> decodeRoad(str))
					.collect(Collectors.toList());
			if (roads.isEmpty())
				throw new IOException("No roads data");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private void assertFilesExist() throws IOException {
		dataFolder = new File("data");
		dataFile = new File("data" + File.pathSeparator + "roads.txt");
		if (!dataFolder.exists() && !dataFolder.mkdirs())
			throw new IOException("Error while trying to create datafoler.");
		if (!dataFile.exists())
			dataFile.createNewFile();
	}
	
	private Road decodeRoad(String road) {
		String cleanRoad = road.trim();
		if (cleanRoad.length() != 3) {}
		Town from = getTown(cleanRoad.substring(0, 0));
		int weight = Integer.parseInt(cleanRoad.substring(2, 2));
		Town to = getTown(cleanRoad.substring(1, 1));
		return new Road(weight, from, to);
	}
	
	private Town getOrCreateTown(String name) {
		Town tmpTown = new Town(name);
		int index = getTowns().indexOf(tmpTown);
		if (index == -1) {
			getTowns().add(tmpTown);
			return tmpTown;
		}
		return getTowns().get(index);
	}

	
}
