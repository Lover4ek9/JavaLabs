package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CivilAircraftTest {
    @Test
    void createsAircraftWithInitialInformation() {
        CivilAircraft plane = new CivilAircraft("Лайнер", 100, true, 150);
        assertEquals("Гражданский самолёт: Лайнер, топливо: 100 л, исправен: true, "
                + "техосмотр: false, пассажиры: 0/150", plane.getInfo());
    }

    @Test
    void rejectsInvalidConstructorArguments() {
        assertThrows(IllegalArgumentException.class, () -> new CivilAircraft(null, 100, true, 150));
        assertThrows(IllegalArgumentException.class, () -> new CivilAircraft(" ", 100, true, 150));
        assertThrows(IllegalArgumentException.class, () -> new CivilAircraft("Лайнер", -1, true, 150));
        assertThrows(IllegalArgumentException.class, () -> new CivilAircraft("Лайнер", 100, true, 0));
        assertThrows(IllegalArgumentException.class, () -> new CivilAircraft("Лайнер", 100, true, -1));
    }

    @Test
    void boardsPassengersInSeveralGroupsUpToCapacity() {
        CivilAircraft plane = new CivilAircraft("Лайнер", 100, true, 150);
        plane.boardPassengers(80);
        plane.boardPassengers(70);
        assertTrue(plane.getInfo().contains("пассажиры: 150/150"));
        String before = plane.getInfo();
        assertThrows(IllegalArgumentException.class, () -> plane.boardPassengers(1));
        assertEquals(before, plane.getInfo());
    }

    @Test
    void rejectsInvalidPassengerCounts() {
        CivilAircraft plane = new CivilAircraft("Лайнер", 100, true, 150);
        String before = plane.getInfo();
        assertThrows(IllegalArgumentException.class, () -> plane.boardPassengers(0));
        assertThrows(IllegalArgumentException.class, () -> plane.boardPassengers(-1));
        assertThrows(IllegalArgumentException.class, () -> plane.boardPassengers(151));
        assertEquals(before, plane.getInfo());
    }

    @Test
    void successfulFlightDisembarksPassengersAndFreesSeats() {
        CivilAircraft plane = new CivilAircraft("Лайнер", 100, true, 150);
        plane.inspect();
        plane.boardPassengers(80);
        plane.performFlight(100);
        assertTrue(plane.getInfo().contains("топливо: 0 л"));
        assertTrue(plane.getInfo().contains("пассажиры: 0/150"));
        plane.boardPassengers(150);
        assertTrue(plane.getInfo().contains("пассажиры: 150/150"));
    }

    @Test
    void failedFlightKeepsPassengersAndFuel() {
        CivilAircraft plane = new CivilAircraft("Лайнер", 100, true, 150);
        plane.inspect();
        plane.boardPassengers(80);
        String before = plane.getInfo();
        assertThrows(IllegalStateException.class, () -> plane.performFlight(101));
        assertEquals(before, plane.getInfo());
    }
}
