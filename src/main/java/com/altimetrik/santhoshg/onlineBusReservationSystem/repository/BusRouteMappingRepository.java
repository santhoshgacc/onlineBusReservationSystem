package com.altimetrik.santhoshg.onlineBusReservationSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.altimetrik.santhoshg.onlineBusReservationSystem.model.BusRouteMapping;
import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Route;

public interface BusRouteMappingRepository extends CrudRepository<BusRouteMapping, Long> {

	public List<BusRouteMapping> findByRoute(Route route);
	
}

