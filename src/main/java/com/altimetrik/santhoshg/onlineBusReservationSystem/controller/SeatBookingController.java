package com.altimetrik.santhoshg.onlineBusReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.santhoshg.onlineBusReservationSystem.exceptions.NotFoundException;
import com.altimetrik.santhoshg.onlineBusReservationSystem.service.SeatBookingService;
import com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model.SeatBookingRequest;
import com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model.SeatBookingResponse;

@RestController
@RequestMapping("/bus")
public class SeatBookingController {

	@Autowired
	SeatBookingService bookingService;
		
	@RequestMapping(method = RequestMethod.POST, value = "/seat")
	public SeatBookingResponse bookRandomSeat(@RequestBody SeatBookingRequest seatReq) {
		if(seatReq.getSeatNum()==0) {
			return bookingService.bookRandomSeat(seatReq).orElseThrow(() -> new NotFoundException("Seat is not available in " + seatReq.getBusId()));	
		} else {
			return bookingService.bookSelectedSeat(seatReq).orElseThrow(() -> new NotFoundException("Seat Number " + seatReq.getSeatNum() + " is not available in " + seatReq.getBusId()));
		}
				
	}
	
	
}
