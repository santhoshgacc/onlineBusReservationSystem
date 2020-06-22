package com.altimetrik.santhoshg.onlineBusReservationSystem;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Bus;
import com.altimetrik.santhoshg.onlineBusReservationSystem.model.BusRouteMapping;
import com.altimetrik.santhoshg.onlineBusReservationSystem.model.Seat;
import com.altimetrik.santhoshg.onlineBusReservationSystem.systemData.SortTypes;
import com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model.BusSearchRequest;
import com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model.BusSearchResponse;
import com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model.SeatBookingRequest;
import com.altimetrik.santhoshg.onlineBusReservationSystem.ui.model.SeatBookingResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineBusReservationSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OnlineBusReservationSystemApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private ObjectMapper objectMapper;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void getBusSearchResultsTest() throws Exception {

		BusSearchRequest searchReq = new BusSearchRequest("CHE", "BLR", LocalDate.now(), null);

		HttpEntity<BusSearchRequest> entity = new HttpEntity<BusSearchRequest>(searchReq, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("bus/search"), HttpMethod.POST,
				entity, String.class);

		String expected = buildBusSearchDefaultResponseJsonString();

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void getSortedBusSearchResultsTest() throws Exception {

		BusSearchResponse searchReq = buildBusSearchDefaultResponse();

		HttpEntity<BusSearchResponse> entity = new HttpEntity<BusSearchResponse>(searchReq, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("bus/search/sort/ARRTIME"),
				HttpMethod.POST, entity, String.class);

		String expected = buildBusArrTimeSortedResponseJsonString();

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void bookRandomSeatTest() throws Exception {

		SeatBookingRequest searchReq = new SeatBookingRequest(1001L, 0, 1000);

		HttpEntity<SeatBookingRequest> entity = new HttpEntity<SeatBookingRequest>(searchReq, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("bus/seat"), HttpMethod.POST, entity,
				String.class);

		assertTrue(response.getStatusCode() == HttpStatus.OK);
	}

	@Test
	public void bookRandomSeat_ResourceNotFoundTest() throws Exception {

		SeatBookingRequest searchReq = new SeatBookingRequest(1001L, 0, 1000);

		HttpEntity<SeatBookingRequest> entity = new HttpEntity<SeatBookingRequest>(searchReq, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("bus/seat"), HttpMethod.POST, entity,
				String.class);
		response = restTemplate.exchange(createURLWithPort("bus/seat"), HttpMethod.POST, entity, String.class);
		response = restTemplate.exchange(createURLWithPort("bus/seat"), HttpMethod.POST, entity, String.class);
		response = restTemplate.exchange(createURLWithPort("bus/seat"), HttpMethod.POST, entity, String.class);
		response = restTemplate.exchange(createURLWithPort("bus/seat"), HttpMethod.POST, entity, String.class);

		assertTrue(response.getStatusCode() == HttpStatus.NOT_FOUND);
	}

	@Test
	public void bookSelectedSeatTest() throws Exception {

		SeatBookingRequest searchReq = new SeatBookingRequest(1000L, 3, 1000);

		HttpEntity<SeatBookingRequest> entity = new HttpEntity<SeatBookingRequest>(searchReq, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("bus/seat"), HttpMethod.POST, entity,
				String.class);

		String expectedResponse = objectMapper
				.writeValueAsString(new SeatBookingResponse(1000L, 3, (1000 + (1000 * .1))));

		JSONAssert.assertEquals(expectedResponse, response.getBody(), false);
	}
	
	@Test
	public void bookSelectedSeat_ResourceNotFoundTest() throws Exception {

		SeatBookingRequest searchReq = new SeatBookingRequest(1000L, 3, 1000);

		HttpEntity<SeatBookingRequest> entity = new HttpEntity<SeatBookingRequest>(searchReq, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("bus/seat"), HttpMethod.POST, entity,
				String.class);

		response = restTemplate.exchange(createURLWithPort("bus/seat"), HttpMethod.POST, entity,
				String.class);
		
		assertTrue(response.getStatusCode() == HttpStatus.NOT_FOUND);
	}

	private String buildBusSearchDefaultResponseJsonString() throws Exception {

		return objectMapper.writeValueAsString(buildBusSearchDefaultResponse());

	}

	private BusSearchResponse buildBusSearchDefaultResponse() {
		List<BusRouteMapping> busRouteList = Arrays.asList(
				new BusRouteMapping(1, 1000L, LocalDate.now(), LocalTime.of(10, 00), LocalTime.of(15, 00), 5.0f, 1000),
				new BusRouteMapping(1, 1001L, LocalDate.now(), LocalTime.of(9, 00), LocalTime.of(13, 00), 4.0f, 1200));
		long counter = 1;
		for (BusRouteMapping busRoute : busRouteList) {
			busRoute.setId(counter++);
			Bus busObj = busRoute.getBus();
			if (busObj.getBusId() == 1000L) {
				busObj.setBusNo("TN001234");
				busObj.setNoOfSeats(5);
				busObj.setOperName("ALT TRAVELS");

				List<Seat> seats = Arrays.asList(new Seat(1L, 1, "Y", "N", "SS", "N", 1000L),
						new Seat(2L, 2, "Y", "N", "SS", "N", 1000L), new Seat(3L, 3, "Y", "Y", "SS", "N", 1000L),
						new Seat(4L, 4, "Y", "N", "SS", "N", 1000L), new Seat(5L, 5, "Y", "N", "SS", "N", 1000L));
				busObj.setSeats(seats);

			} else if (busObj.getBusId() == 1001L) {
				busObj.setBusNo("KA009876");
				busObj.setNoOfSeats(4);
				busObj.setOperName("ESC TRAVELS");

				List<Seat> seats = Arrays.asList(new Seat(6L, 1, "Y", "N", "SL", "N", 1001L),
						new Seat(7L, 2, "Y", "Y", "SL", "N", 1001L), new Seat(8L, 3, "Y", "N", "SL", "N", 1001L),
						new Seat(9L, 4, "Y", "N", "SL", "N", 1001L));

				busObj.setSeats(seats);

			}

			busRoute.getRoute().setSource("CHE");
			busRoute.getRoute().setDestination("BLR");

		}

		return new BusSearchResponse(busRouteList, SortTypes.PRI.getType(), busRouteList.size());

	}

	private String buildBusArrTimeSortedResponseJsonString() throws Exception {

		return objectMapper.writeValueAsString(buildBusArrTimeSortedResponse());

	}

	private BusSearchResponse buildBusArrTimeSortedResponse() {
		List<BusRouteMapping> busRouteList = Arrays.asList(
				new BusRouteMapping(1, 1001L, LocalDate.now(), LocalTime.of(9, 00), LocalTime.of(13, 00), 4.0f, 1200),
				new BusRouteMapping(1, 1000L, LocalDate.now(), LocalTime.of(10, 00), LocalTime.of(15, 00), 5.0f, 1000));
		for (BusRouteMapping busRoute : busRouteList) {

			Bus busObj = busRoute.getBus();
			if (busObj.getBusId() == 1000L) {
				busRoute.setId(1L);
				busObj.setBusNo("TN001234");
				busObj.setNoOfSeats(5);
				busObj.setOperName("ALT TRAVELS");

				List<Seat> seats = Arrays.asList(new Seat(1L, 1, "Y", "N", "SS", "N", 1000L),
						new Seat(2L, 2, "Y", "N", "SS", "N", 1000L), new Seat(3L, 3, "Y", "Y", "SS", "N", 1000L),
						new Seat(4L, 4, "Y", "N", "SS", "N", 1000L), new Seat(5L, 5, "Y", "N", "SS", "N", 1000L));
				busObj.setSeats(seats);

			} else if (busObj.getBusId() == 1001L) {
				busRoute.setId(2L);
				busObj.setBusNo("KA009876");
				busObj.setNoOfSeats(4);
				busObj.setOperName("ESC TRAVELS");

				List<Seat> seats = Arrays.asList(new Seat(6L, 1, "Y", "N", "SL", "N", 1001L),
						new Seat(7L, 2, "Y", "Y", "SL", "N", 1001L), new Seat(8L, 3, "Y", "N", "SL", "N", 1001L),
						new Seat(9L, 4, "Y", "N", "SL", "N", 1001L));

				busObj.setSeats(seats);

			}

			busRoute.getRoute().setSource("CHE");
			busRoute.getRoute().setDestination("BLR");

		}

		return new BusSearchResponse(busRouteList, SortTypes.ARRTIME.getType(), busRouteList.size());

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
