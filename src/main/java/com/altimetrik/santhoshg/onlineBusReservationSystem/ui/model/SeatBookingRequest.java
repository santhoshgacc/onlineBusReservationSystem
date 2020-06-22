package com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class SeatBookingRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotBlank
	private long busId;
	private int seatNum;
	@NotBlank
	private double ticketPrice;
		
	public SeatBookingRequest() {}
	
	public SeatBookingRequest(@NotBlank long busId, int seatNum, @NotBlank double ticketPrice) {
		super();
		this.busId = busId;
		this.seatNum = seatNum;
		this.ticketPrice = ticketPrice;
	}

	public long getBusId() {
		return busId;
	}
	
	public void setBusId(long busId) {
		this.busId = busId;
	}
	
	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
}
