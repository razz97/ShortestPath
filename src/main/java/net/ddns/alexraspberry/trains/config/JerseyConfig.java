package net.ddns.alexraspberry.trains.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import net.ddns.alexraspberry.trains.beans.rest.RESTControllerBean;

/**
 * Responsible for registering JAX-RS controllers
 * @author alex
 *
 */
@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(RESTControllerBean.class);
	}
}
