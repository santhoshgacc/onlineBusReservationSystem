package com.altimetrik.santhoshg.onlineBusReservationSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Bus;
import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Seat;
import com.altimetrik.santhoshg.onlineBusReservationSystem.repository.SeatRepository;
import com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model.SeatBookingRequest;
import com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model.SeatBookingResponse;

@Service
public class SeatBookingService {

	@Autowired
	private SeatRepository seatRepository;

	public Optional<SeatBookingResponse> bookRandomSeat(SeatBookingRequest seatReq) {
		Seat seat = seatRepository.findByBus(new Bus(seatReq.getBusId(),"","",0)).parallelStream()
																				 .filter(s -> (s.getAvailable().equals("Y") && s.getOnHold().equals("N")))
																				 .findAny()
																				 .orElse(null);
																				 
		if(seat!=null) {
			seat.setOnHold("Y");
			seat = seatRepository.save(seat);
			return Optional.of(new SeatBookingResponse(seatReq.getBusId(), seat.getSeatNo(), seatReq.getTicketPrice()));		
		} 

		return Optional.empty();
		
	}

	public Optional<SeatBookingResponse> bookSelectedSeat(SeatBookingRequest seatReq) {
		Seat seat = seatRepository.findByBusAndSeatNo(new Bus(seatReq.getBusId(),"","",0),seatReq.getSeatNum());
		if(seat.getAvailable().equals("Y") && seat.getOnHold().equals("N")) {
			seat.setOnHold("Y");
			seat = seatRepository.save(seat);
			return Optional.of(new SeatBookingResponse(seatReq.getBusId(), seat.getSeatNo(), (seatReq.getTicketPrice()+(seatReq.getTicketPrice()*.1))));
		} else {
			return Optional.empty();
		}
	}
	
}
