package com.altimetrik.santhoshg.onlineBusReservationSystem.repository;

import java.util.List;



import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Bus;
import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Seat;

public interface SeatRepository extends CrudRepository<Seat, Long> {

	
	public List<Seat> findByBus(Bus bus);
	
	
	@Transactional(timeout=120)
	public Seat findByBusAndSeatNo(Bus bus, int seatNo);
	
	
}

