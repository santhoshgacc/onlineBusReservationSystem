package com.altimetrik.santhoshg.onlineBusReservationSystem.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Bus;
import com.altimetrik.santhoshg.onlineBusReservationSystem.model.BusRouteMapping;
import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Route;
import com.altimetrik.santhoshg.onlineBusReservationSystem.repository.BusRepository;
import com.altimetrik.santhoshg.onlineBusReservationSystem.repository.BusRouteMappingRepository;
import com.altimetrik.santhoshg.onlineBusReservationSystem.repository.RouteRepository;
import com.altimetrik.santhoshg.onlineBusReservationSystem.systemData.SeatTypes;
import com.altimetrik.santhoshg.onlineBusReservationSystem.systemData.SortTypes;
import com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model.BusSearchRequest;
import com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model.BusSearchResponse;

@Service
public class BusSearchService {

	@Autowired
	private BusRepository busRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private BusRouteMappingRepository busRouteMappingRepository;

	public Bus getBusDetails(Long id) {
		return busRepository.findById(id).get();
	}

	public BusSearchResponse getBusSearchResults(BusSearchRequest busSearchCriteria) {
		Route route = routeRepository.findBySourceAndDestination(busSearchCriteria.getSource(),
				busSearchCriteria.getDestination());
		List<BusRouteMapping> busRouteList = busRouteMappingRepository.findByRoute(route);
		busRouteList = busRouteList.parallelStream()
				.filter(o -> o.getTravelDate().equals(busSearchCriteria.getTravelDate()))
				.sorted(Comparator.comparingInt(BusRouteMapping::getPrice)).collect(Collectors.toList());

		return new BusSearchResponse(busRouteList, SortTypes.PRI.getType(), busRouteList.size());

	}

	public BusSearchResponse getSortedBusSearchResults(BusSearchResponse busSearchResponse, String type) {
		List<BusRouteMapping> busRouteList = null;
		SortTypes sortType = SortTypes.valueOf(type);
		switch (sortType) {
		case PRI:
			busRouteList = busSearchResponse.getBusRouteList().parallelStream()
															  .sorted(Comparator.comparingInt(BusRouteMapping::getPrice))
															  .collect(Collectors.toList());
			return new BusSearchResponse(busRouteList, SortTypes.PRI.getType(), busRouteList.size());
		case OPERNAME:
			busRouteList = busSearchResponse.getBusRouteList().parallelStream()
															  .sorted((o1,o2) -> o1.getBus().getOperName().compareTo(o2.getBus().getOperName()))
															  .collect(Collectors.toList());
			return new BusSearchResponse(busRouteList, SortTypes.OPERNAME.getType(), busRouteList.size());
		case DEPTIME:
			busRouteList = busSearchResponse.getBusRouteList().parallelStream()
															  .sorted((o1,o2) -> o1.getDepTime().compareTo(o2.getDepTime()))
															  .collect(Collectors.toList());
			return new BusSearchResponse(busRouteList, SortTypes.DEPTIME.getType(), busRouteList.size());
		case ARRTIME:
			busRouteList = busSearchResponse.getBusRouteList().parallelStream()
															  .sorted((o1,o2) -> o1.getArrTime().compareTo(o2.getArrTime()))
															  .collect(Collectors.toList());
			return new BusSearchResponse(busRouteList, SortTypes.ARRTIME.getType(), busRouteList.size());
		case DUR:
			busRouteList = busSearchResponse.getBusRouteList().parallelStream()
															  .sorted(Comparator.comparingDouble(BusRouteMapping::getDuration))
															  .collect(Collectors.toList());
			return new BusSearchResponse(busRouteList, SortTypes.DUR.getType(), busRouteList.size());
		}
		return null;
	}
	
	public BusSearchResponse getFilteredBusSearchResults(BusSearchResponse busSearchResponse, String type) {
		List<BusRouteMapping> busRouteList = null;
		SeatTypes seatType = SeatTypes.valueOf(type);
		switch (seatType) {
		case SS:
			busRouteList = busSearchResponse.getBusRouteList().parallelStream()
			  												  .filter(o -> o.getBus().getSeats().parallelStream()
			  														                 .anyMatch(t -> t.getSeatType().equals(SeatTypes.SS.getType())))
			  												  .collect(Collectors.toList());
			return new BusSearchResponse(busRouteList, SortTypes.PRI.getType(), busRouteList.size());
		case SL:
			busRouteList = busSearchResponse.getBusRouteList().parallelStream()
															  .filter(o -> o.getBus().getSeats().parallelStream()
																	  				 .anyMatch(t -> t.getSeatType().equals(SeatTypes.SL.getType())))
															  .collect(Collectors.toList());
			return new BusSearchResponse(busRouteList, SortTypes.PRI.getType(), busRouteList.size());
		}
		return null;
	}
	
}
