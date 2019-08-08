package net.ddns.alexraspberry.trains.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Town {
	
	@NonNull @EqualsAndHashCode.Include
	private String name;
	
	@XmlTransient
	private List<Road> roadsOut;
	
	private boolean isVisited;
	
	public Town(String name) {
		this.roadsOut = new ArrayList<>();
		this.isVisited = false;
		this.name = name;
	}
	
	public boolean isConnectedTo(Town town) {
		return roadsOut.stream().anyMatch(r -> r.isDestination(town));
	}
	
	@Override
	public String toString() {
		return name;
	}
}
