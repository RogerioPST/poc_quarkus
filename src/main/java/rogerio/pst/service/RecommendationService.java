package rogerio.pst.service;


import java.util.Arrays;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecommendationService {
	List<String> products;

	/*
	 * The methods annotated
with those annotations will be called after object creation for PostConstruct and
before the object is destroyed for PreDestroy:

Ideas for this would include
closing connections, cleaning up resources, and finalizing logging.

	 */
	@PostConstruct
	public void init() {
		products = Arrays.asList("Orange", "Apple", "Mango");
		System.out.println("Products initialized");
	}

	@PreDestroy
	public void cleanup() {
		products = null;
		System.out.println("Products cleaned up");
	}

	public List<String> getProducts() {
		return products;
	}
}

