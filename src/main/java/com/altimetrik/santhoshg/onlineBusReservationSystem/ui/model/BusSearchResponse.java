package com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model;

import java.io.Serializable;
import java.util.List;

import com.altimetrik.santhoshg.onlineBusReservationSystem.model.BusRouteMapping;

public class BusSearchResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<BusRouteMapping> busRouteList;
	private String sortedBy;
	private int searchResultSize;
	
	public BusSearchResponse() {}
	
	public BusSearchResponse(List<BusRouteMapping> busRouteList, String sortedBy, int searchResultSize) {
		super();
		this.busRouteList = busRouteList;
		this.sortedBy = sortedBy;
		this.searchResultSize = searchResultSize;
	}

	public List<BusRouteMapping> getBusRouteList() {
		return busRouteList;
	}
	
	public void setBusRouteList(List<BusRouteMapping> busRouteList) {
		this.busRouteList = busRouteList;
	}
	
	public String getSortedBy() {
		return sortedBy;
	}
	
	public void setSortedBy(String sortedBy) {
		this.sortedBy = sortedBy;
	}
	
	public int getSearchResultSize() {
		return searchResultSize;
	}
	
	public void setSearchResultSize(int searchResultSize) {
		this.searchResultSize = searchResultSize;
	}
			
}
