package com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BusSearchRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="Source City is Required")
	private String source;
	@NotBlank(message="Destination City is Required")
	private String destination;
	@NotBlank(message="Travel Date is Required")
	@JsonFormat(pattern="dd-MMM-yyyy")
	private LocalDate travelDate;
	@JsonFormat(pattern="dd-MMM-yyyy")
	private LocalDate returnDate;
	
	public BusSearchRequest() {}
	
	public BusSearchRequest(@NotBlank(message = "Source City is Required") String source,
			@NotBlank(message = "Destination City is Required") String destination,
			@NotBlank(message = "Travel Date is Required") LocalDate travelDate, LocalDate returnDate) {
		super();
		this.source = source;
		this.destination = destination;
		this.travelDate = travelDate;
		this.returnDate = returnDate;
	}

	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public LocalDate getTravelDate() {
		return travelDate;
	}
	
	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}
	
	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
}
