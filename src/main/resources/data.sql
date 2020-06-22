--BUS TABLE DATA - STARTS--
INSERT INTO BUS(bus_Id, bus_No, oper_Name, no_Of_Seats)
VALUES (1000, 'TN001234', 'ALT TRAVELS', 5);

INSERT INTO BUS(bus_Id, bus_No, oper_Name, no_Of_Seats)
VALUES (1001, 'KA009876', 'ESC TRAVELS', 4);
--BUS TABLE DATA - ENDS--

--SEAT TABLE DATA - STARTS--
INSERT INTO SEAT(seat_No, available, ladies_Seat, seat_Type, on_Hold, bus_Id)
VALUES (1, 'Y', 'N', 'SS', 'N', 1000);

INSERT INTO SEAT(seat_No, available, ladies_Seat, seat_Type, on_Hold, bus_Id)
VALUES (2, 'Y', 'N', 'SS', 'N', 1000);

INSERT INTO SEAT(seat_No, available, ladies_Seat, seat_Type, on_Hold, bus_Id)
VALUES (3, 'Y', 'Y', 'SS', 'N', 1000);

INSERT INTO SEAT(seat_No, available, ladies_Seat, seat_Type, on_Hold, bus_Id)
VALUES (4, 'Y', 'N', 'SS', 'N', 1000);

INSERT INTO SEAT(seat_No, available, ladies_Seat, seat_Type, on_Hold, bus_Id)
VALUES (5, 'Y', 'N', 'SS', 'N', 1000);

INSERT INTO SEAT(seat_No, available, ladies_Seat, seat_Type, on_Hold, bus_Id)
VALUES (1, 'Y', 'N', 'SL', 'N', 1001);

INSERT INTO SEAT(seat_No, available, ladies_Seat, seat_Type, on_Hold, bus_Id)
VALUES (2, 'Y', 'Y', 'SL', 'N', 1001);

INSERT INTO SEAT(seat_No, available, ladies_Seat, seat_Type, on_Hold, bus_Id)
VALUES (3, 'Y', 'N', 'SL', 'N', 1001);

INSERT INTO SEAT(seat_No, available, ladies_Seat, seat_Type, on_Hold, bus_Id)
VALUES (4, 'Y', 'N', 'SL', 'N', 1001);
--SEAT TABLE DATA - ENDS--

--ROUTE TABLE DATA - STARTS--
INSERT INTO ROUTE(id, source, destination)
VALUES (1, 'CHE', 'BLR');

INSERT INTO ROUTE(id, source, destination)
VALUES (2, 'BLR', 'CHE');
--ROUTE TABLE DATA - ENDS--

--BUSROUTEMAPPING TABLE DATA - STARTS--
INSERT INTO BUS_ROUTE_MAPPING(route_Id, bus_Id, travel_Date, dep_Time, arr_Time, duration, price)
VALUES (1, 1000, CURRENT_DATE, '10:00', '15:00', 5.0, 1000);

INSERT INTO BUS_ROUTE_MAPPING(route_Id, bus_Id, travel_Date, dep_Time, arr_Time, duration, price)
VALUES (1, 1001, CURRENT_DATE, '09:00', '13:00', 4.0, 1200);

INSERT INTO BUS_ROUTE_MAPPING(route_Id, bus_Id, travel_Date, dep_Time, arr_Time, duration, price)
VALUES (2, 1000, CURRENT_DATE, '16:00', '21:00', 5.0, 1000);

INSERT INTO BUS_ROUTE_MAPPING(route_Id, bus_Id, travel_Date, dep_Time, arr_Time, duration, price)
VALUES (2, 1001, CURRENT_DATE, '13:00', '17:00', 4.0, 1200);
--BUSROUTEMAPPING TABLE DATA - ENDS--

