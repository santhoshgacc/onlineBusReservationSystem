package com.altimetrik.santhoshg.onlineBusReservationSystem.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BusRouteMapping implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="route_id", referencedColumnName="id")
	private Route route;
	@ManyToOne
	@JoinColumn(name="bus_id",referencedColumnName="bus_Id")
	private Bus bus;
	private LocalDate travelDate;
	private LocalTime depTime;
	private LocalTime arrTime;
	private float duration;
	private int price;
	
	public BusRouteMapping() {}
	
	public BusRouteMapping(long routeId, long busId, LocalDate travelDate, LocalTime depTime, LocalTime arrTime,
			float duration, int price) {
		super();
		this.route = new Route(routeId, "", "");
		this.bus = new Bus(busId, "", "", 0);
		this.travelDate = travelDate;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.duration = duration;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public LocalTime getDepTime() {
		return depTime;
	}

	public void setDepTime(LocalTime depTime) {
		this.depTime = depTime;
	}

	public LocalTime getArrTime() {
		return arrTime;
	}

	public void setArrTime(LocalTime arrTime) {
		this.arrTime = arrTime;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
