package com.altimetrik.santhoshg.onlineBusReservationSystem.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Seat implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seatId;
	private int seatNo;
	private String available;
	private String ladiesSeat;
	private String seatType;
	private String onHold;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "bus_Id", nullable=false)
	private Bus bus;

	public Seat() {}
	
	public Seat(Long seatId, int seatNo, String available, String ladiesSeat, String seatType, String onHold,
			Long busId) {
		super();
		this.seatId = seatId;
		this.seatNo = seatNo;
		this.available = available;
		this.ladiesSeat = ladiesSeat;
		this.seatType = seatType;
		this.onHold = onHold;
		this.bus = new Bus(busId, "", "", 0);
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getLadiesSeat() {
		return ladiesSeat;
	}

	public void setLadiesSeat(String ladiesSeat) {
		this.ladiesSeat = ladiesSeat;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getOnHold() {
		return onHold;
	}

	public void setOnHold(String onHold) {
		this.onHold = onHold;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

}
