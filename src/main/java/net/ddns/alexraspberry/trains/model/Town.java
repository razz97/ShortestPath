package net.ddns.alexraspberry.trains.model;

import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Town {
	
	@NonNull @EqualsAndHashCode.Include
	private String name;
	
	@XmlTransient
	private List<Road> roadsOut;
	
	public boolean isConnectedTo(Town town) {
		return roadsOut.stream().anyMatch(r -> r.isDestination(town));
	}
	
	@Override
	public String toString() {
		return name;
	}
}
