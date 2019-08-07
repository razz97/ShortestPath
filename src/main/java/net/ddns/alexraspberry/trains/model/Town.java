package net.ddns.alexraspberry.trains.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;

@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Getter @Setter
public class Town {
	
	@NonNull
	private String name;
	
	@Delegate @EqualsAndHashCode.Exclude
	private List<Road> roadsOut;
	
	public boolean isConnectedTo(Town town) {
		return roadsOut.stream().anyMatch(r -> r.isDestination(town));
	}
	
	@Override
	public String toString() {
		return name;
	}
}
