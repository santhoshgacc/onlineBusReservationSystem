package com.altimetrik.santhoshg.onlineBusReservationSystem.systemData;

public enum SortTypes {

	PRI("PRI"), OPERNAME("OPERNAME"), DEPTIME("DEPTIME"), ARRTIME("ARRTIME"), DUR("DUR");
	
	private String type;
	
	SortTypes(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
