package net.ddns.alexraspberry.trains.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import net.ddns.alexraspberry.trains.service.RouteService;
import net.ddns.alexraspberry.trains.service.TownService;

@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(RouteService.class);
		register(TownService.class);
	}
}
