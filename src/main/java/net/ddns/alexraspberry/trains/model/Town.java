package net.ddns.alexraspberry.trains.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.ddns.alexraspberry.trains.exception.InvalidActionException;
import net.ddns.alexraspberry.trains.exception.InvalidActionException.Cause;


/**
 * Represents a town, which essentially is a node in a graph with a name.
 * @author alex
 *
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Town implements Comparable<Town> {
	
	@NonNull @EqualsAndHashCode.Include
	private String name;
	
	@XmlTransient
	private List<Road> roadsOut;
	
	// For dijkstra's search algorithm
	@XmlTransient
	private Town previous;
	@XmlTransient
	private int distanceToStart;
	
	public Town(String name) {
		this.roadsOut = new ArrayList<>();
		this.distanceToStart = Integer.MAX_VALUE;
		this.name = name;
	}
	
	/**
	 * Computes the distance between two adjacent towns.
	 * @throws InvalidActionException if the towns are not connected
	 */
	public int distanceTo(Town town) throws InvalidActionException {		
		return roadsOut.stream()
				.filter(r -> r.getDestination().equals(town))
				.mapToInt(ro -> ro.getWeight())
				.findAny().orElseThrow(() -> new InvalidActionException(Cause.TOWNS_NOT_CONNECTED));
	}
	
	/**
	 * True if the town is connected to the argument town.
	 */
	public boolean isConnectedTo(Town town) {
		return roadsOut.stream()
				.anyMatch(r -> r.getDestination().equals(town));
	}
	
	@Override
    public int compareTo(Town other)
    {
        return Integer.compare(distanceToStart, other.distanceToStart);
    }
	
	@Override
	public String toString() {
		return name;
	}
}
