package com.altimetrik.santhoshg.onlineBusReservationSystem.systemData;

public enum SeatTypes {

	SS("SS"), SL("SL");
	
	private String type;
	
	SeatTypes(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
