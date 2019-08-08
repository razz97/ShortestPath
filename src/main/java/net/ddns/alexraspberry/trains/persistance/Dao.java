package net.ddns.alexraspberry.trains.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ddns.alexraspberry.trains.beans.QueryController;
import net.ddns.alexraspberry.trains.model.Road;
import net.ddns.alexraspberry.trains.model.Town;

@Repository
public class Dao implements IDao {

	private File dataFolder;
	private File dataFile;
	
	private List<Town> towns;
	private List<Road> roads;
	
	@Autowired
	private QueryController controller;
	
	@PostConstruct
	public void init() throws IOException {
		towns = new ArrayList<>();
		roads = new ArrayList<>();
		assertFilesExist();
		loadDataIntoMemory();
		controller.resolveQuery(towns);
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
	
	private void loadDataIntoMemory() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
			roads = br.lines()
					.map(str -> decodeRoad(str))
					.collect(Collectors.toList());
			if (roads.isEmpty())
				throw new IOException("No roads data");
		}
	}
	
	private void assertFilesExist() throws IOException {
		dataFolder = new File("data");
		dataFile = new File("data" + File.separatorChar + "roads.txt");
		if (!dataFolder.exists() && !dataFolder.mkdirs())
			throw new IOException("Error while trying to create datafoler.");
		if (!dataFile.exists())
			dataFile.createNewFile();
	}
	
	private Road decodeRoad(String road) {
		String cleanRoad = road.trim();
		if (cleanRoad.length() != 3) {}
		Town from = getOrCreateTown(cleanRoad.substring(0, 1));
		int weight = Integer.parseInt(cleanRoad.substring(2, 3));
		Town to = getOrCreateTown(cleanRoad.substring(1, 2));
		return new Road(weight, from, to);
	}
	
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
