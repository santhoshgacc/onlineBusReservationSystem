package com.altimetrik.santhoshg.onlineBusReservationSystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Route;

public interface RouteRepository extends CrudRepository<Route, Long> {

	public Route findBySourceAndDestination(String source, String destination);
	
}

