package org.example;

import org.example.factory.AircraftFactory;
import org.example.factory.CargoAircraftFactory;
import org.example.factory.PassengerAircraftFactory;
import org.example.model.Aircraft;
import org.example.model.Airline;
import org.example.model.CargoAircraft;
import org.example.model.PassengerAircraft;
import org.example.service.AirlineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirlineServiceTest {
    private final AirlineService service = new AirlineService();

    @TempDir
    Path tempDir;

    private Airline createAirline() {
        Airline airline = new Airline();
        airline.addAircraft(new PassengerAircraft("Сокол", 3000, 1000, 100));
        airline.addAircraft(new CargoAircraft("Грузовик", 2000, 1500, 20));
        airline.addAircraft(new PassengerAircraft("Стриж", 5000, 2000, 150));
        airline.addAircraft(new CargoAircraft("Атлант", 4000, 2500, 30));
        return airline;
    }

    @Test
    void createsPassengerThroughFactoryMethod() {
        AircraftFactory factory = new PassengerAircraftFactory();
        Aircraft plane = factory.createAircraft("Сокол", 3000, 1000, 100);
        PassengerAircraft passenger = assertInstanceOf(PassengerAircraft.class, plane);
        assertEquals("Сокол", passenger.getName());
        assertEquals(3000, passenger.getFlightRange());
        assertEquals(1000, passenger.getFuelConsumption());
        assertEquals(100, passenger.getPassengerCapacity());
    }

    @Test
    void createsCargoThroughFactoryMethod() {
        AircraftFactory factory = new CargoAircraftFactory();
        CargoAircraft cargo = assertInstanceOf(CargoAircraft.class,
                factory.createAircraft("Грузовик", 2000, 1500, 20));
        assertEquals("Грузовик", cargo.getName());
        assertEquals(2000, cargo.getFlightRange());
        assertEquals(1500, cargo.getFuelConsumption());
        assertEquals(20, cargo.getCargoCapacity());
    }

    @Test
    void rejectsInvalidAircraft() {
        assertThrows(IllegalArgumentException.class, () -> new PassengerAircraft(" ", 100, 10, 1));
        assertThrows(IllegalArgumentException.class, () -> new CargoAircraft("A", 0, 10, 1));
        assertThrows(IllegalArgumentException.class, () -> new CargoAircraft("A", 100, -1, 1));
        assertThrows(IllegalArgumentException.class, () -> new PassengerAircraft("A", 100, 10, 0));
        assertThrows(IllegalArgumentException.class, () -> new CargoAircraft("A", 100, 10, -1));
    }

    @Test
    void calculatesTotalPassengerCapacity() {
        assertEquals(250, service.getTotalPassengerCapacity(createAirline()));
    }

    @Test
    void calculatesTotalCargoCapacity() {
        assertEquals(50, service.getTotalCargoCapacity(createAirline()));
    }

    @Test
    void sortsByRangeAndKeepsOriginalOrder() {
        Airline airline = createAirline();
        List<Aircraft> original = airline.getAircraft();
        List<Aircraft> sorted = service.sortByFlightRange(airline);
        assertEquals(List.of(original.get(1), original.get(0), original.get(3), original.get(2)), sorted);
        assertEquals(original, airline.getAircraft());
    }

    @Test
    void findsAircraftIncludingBothBoundaries() {
        Airline airline = createAirline();
        assertEquals(airline.getAircraft().subList(0, 3),
                service.findByFuelConsumption(airline, 1000, 2000));
        assertEquals(List.of(airline.getAircraft().get(1)),
                service.findByFuelConsumption(airline, 1500, 1500));
        assertTrue(service.findByFuelConsumption(airline, 3000, 4000).isEmpty());
    }

    @Test
    void rejectsInvalidSearchRange() {
        assertThrows(IllegalArgumentException.class,
                () -> service.findByFuelConsumption(createAirline(), 2000, 1000));
        assertThrows(IllegalArgumentException.class,
                () -> service.findByFuelConsumption(createAirline(), -1, 1000));
    }

    @Test
    void handlesEmptyAirline() {
        Airline airline = new Airline();
        assertEquals(0, service.getTotalPassengerCapacity(airline));
        assertEquals(0, service.getTotalCargoCapacity(airline));
        assertTrue(service.sortByFlightRange(airline).isEmpty());
        assertTrue(service.findByFuelConsumption(airline, 0, 2000).isEmpty());
    }

    @Test
    void skipsBadLinesAndContinuesReading() throws IOException {
        Path file = tempDir.resolve("aircraft.txt");
        Files.writeString(file, """
                # Комментарий

                PASSENGER;Первый;3000;1000;100
                UNKNOWN;Ошибка;1000;1000;1
                CARGO;Ошибка;abc;1000;1
                CARGO;Ошибка;1000;1000;-5
                PASSENGER;Ошибка;1000
                CARGO;;1000;1000;1
                CARGO;Ошибка;0;1000;1
                CARGO;Ошибка;1000;0;1
                PASSENGER;Ошибка;1000;1000;0
                CARGO;Ошибка;1000;1000;
                CARGO;Ошибка;1000;1000;1;лишнее
                  CARGO ; Последний ; 2000 ; 1500 ; 20
                """);
        Airline airline = service.loadFromFile(file);
        assertEquals(2, airline.getAircraft().size());
        assertEquals("Первый", airline.getAircraft().get(0).getName());
        assertEquals("Последний", airline.getAircraft().get(1).getName());
        assertEquals(100, service.getTotalPassengerCapacity(airline));
        assertEquals(20, service.getTotalCargoCapacity(airline));
    }

    @Test
    void reportsMissingFile() {
        assertThrows(IOException.class, () -> service.loadFromFile(tempDir.resolve("missing.txt")));
    }
}
