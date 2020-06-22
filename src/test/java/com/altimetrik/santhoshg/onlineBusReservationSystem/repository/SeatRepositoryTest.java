package com.altimetrik.santhoshg.onlineBusReservationSystem.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Bus;
import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Seat;

@DataJpaTest
public class SeatRepositoryTest {

	@Autowired
	private SeatRepository seatRepository;

	@Test
	public void whenInitializedByDbUnit_thenFindByBus() {
		List<Seat> seats = seatRepository.findByBus(new Bus(1000L,"","",0));
		assertThat(seats).isNotNull();
		assertThat(seats).isNotEmpty();
	}

	@Test
	public void whenInitializedByDbUnit_thenFindByBusAndSeatNo() {
		Seat seat = seatRepository.findByBusAndSeatNo(new Bus(1000L,"","",0), 1);
		assertThat(seat).isNotNull();
	}
	
	@Test
	public void whenInitializedByDbUnit_thenSaveSeat() {
		Seat seat = seatRepository.save(new Seat(1L,1,"Y","N","SS","N",1000L));
		assertThat(seat).isNotNull();
	}
}
