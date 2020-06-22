package com.altimetrik.santhoshg.onlineBusReservationSystem.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.altimetrik.santhoshg.onlineBusReservationSystem.model.BusRouteMapping;
import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Route;

@DataJpaTest
public class BusRouteMappingRepositoryTest {

	@Autowired
	private BusRouteMappingRepository busRouteMappingRepository;

	@Test
	public void whenInitializedByDbUnit_thenFindByRoute() {
		List<BusRouteMapping> busRouteList = busRouteMappingRepository.findByRoute(new Route(1L,"TEST1","TEST2"));
		assertThat(busRouteList).isNotNull();
		assertThat(busRouteList).isNotEmpty();
	}

	@Test
	public void whenInitializedByDbUnit_thenSaveBusRouteMapping() {
		BusRouteMapping busRouteMapping = busRouteMappingRepository.save(new BusRouteMapping(1L, 1000L, LocalDate.now(), LocalTime.of(5, 30), LocalTime.of(10, 30), 5, 1500));
		assertThat(busRouteMapping).isNotNull();
	}
}
