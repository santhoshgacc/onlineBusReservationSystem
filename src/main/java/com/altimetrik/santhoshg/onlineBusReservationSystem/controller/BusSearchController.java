package com.altimetrik.santhoshg.onlineBusReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Bus;
import com.altimetrik.santhoshg.onlineBusReservationSystem.service.BusSearchService;
import com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model.BusSearchRequest;
import com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model.BusSearchResponse;

@RestController
@RequestMapping("/bus")
public class BusSearchController {

	@Autowired
	BusSearchService busSearchService;
		
	@RequestMapping("/{id}")
	public Bus getBusDetails(@PathVariable Long id) {
		return busSearchService.getBusDetails(id);		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/search")
	public BusSearchResponse getBusSearchResults(@RequestBody BusSearchRequest busSearchCriteria) {
		return busSearchService.getBusSearchResults(busSearchCriteria);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/search/sort/{type}")
	public BusSearchResponse getSortedBusSearchResults(@RequestBody BusSearchResponse busSearchResponse, @PathVariable String type) {
		return busSearchService.getSortedBusSearchResults(busSearchResponse, type);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/search/filter/{type}")
	public BusSearchResponse getFilteredBusSearchResults(@RequestBody BusSearchResponse busSearchResponse, @PathVariable String type) {
		return busSearchService.getFilteredBusSearchResults(busSearchResponse, type);
	}
}
