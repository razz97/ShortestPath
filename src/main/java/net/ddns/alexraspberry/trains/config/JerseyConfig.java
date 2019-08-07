package net.ddns.alexraspberry.trains.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import net.ddns.alexraspberry.trains.beans.rest.RESTController;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(RESTController.class);
	}
}
