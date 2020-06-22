package com.altimetrik.santhoshg.onlineBusReservationSystem.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Route;

@DataJpaTest
public class RouteRepositoryTest {

	@Autowired
	private RouteRepository routeRepository;

	@Test
	public void whenInitializedByDbUnit_thenFindById() {
		Route route = routeRepository.findById(1L).get();
		assertThat(route).isNotNull();
	}

	@Test
	public void whenInitializedByDbUnit_thenFindBySourceAndDestination() {
		Route route = routeRepository.findBySourceAndDestination("CHE", "BLR");
		assertThat(route).isNotNull();
	}
	
	@Test
	public void whenInitializedByDbUnit_thenSaveRoute() {
		Route route = routeRepository.save(new Route(1L,"TEST1", "TEST2"));
		assertThat(route).isNotNull();
	}
}
