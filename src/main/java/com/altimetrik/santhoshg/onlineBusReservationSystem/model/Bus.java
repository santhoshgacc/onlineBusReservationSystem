package com.altimetrik.santhoshg.onlineBusReservationSystem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bus implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="bus_Id")
	private Long busId;
	private String busNo;
	private String operName;
	private int noOfSeats;
	
	@OneToMany(mappedBy="bus", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Seat> seats = new  ArrayList<Seat>();
	
	public Bus() {}
	
	public Bus(Long busId, String busNo, String operName, int noOfSeats) {
		super();
		this.busId=busId;
		this.busNo=busNo;
		this.operName=operName;
		this.noOfSeats = noOfSeats;
		this.seats = IntStream.range(0, noOfSeats).mapToObj(x -> new Seat()).collect(Collectors.toList());	
	}
	
	
	public Long getBusId() {
		return busId;
	}
	
	public void setBusId(Long busId) {
		this.busId = busId;
	}
	
	public String getBusNo() {
		return busNo;
	}
	
	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
	
	public String getOperName() {
		return operName;
	}
	
	public void setOperName(String operName) {
		this.operName = operName;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	
	public List<Seat> getSeats() {
		return seats.parallelStream().sorted(Comparator.comparingInt(Seat::getSeatNo)).collect(Collectors.toList());
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public long getAvailableSeats() {
		return seats.parallelStream().filter(o -> o.getAvailable().equals("Y")).count();
	}
}
