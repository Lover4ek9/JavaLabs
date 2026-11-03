package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AircraftTest {
    private Aircraft createAircraft(boolean military, boolean serviceable) {
        if (military) {
            return new MilitaryAircraft("Сокол", 100, serviceable);
        }
        return new CivilAircraft("Лайнер", 100, serviceable, 150);
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void inspectionDependsOnServiceability(boolean military) {
        assertTrue(createAircraft(military, true).inspect());
        assertFalse(createAircraft(military, false).inspect());
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void flightRequiresInspection(boolean military) {
        Aircraft plane = createAircraft(military, true);
        String before = plane.getInfo();
        assertThrows(IllegalStateException.class, () -> plane.performFlight(20));
        assertEquals(before, plane.getInfo());
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void repairRestoresServiceabilityButRequiresInspection(boolean military) {
        Aircraft plane = createAircraft(military, false);
        assertFalse(plane.inspect());
        plane.repair();
        assertThrows(IllegalStateException.class, () -> plane.performFlight(20));
        assertTrue(plane.inspect());
        assertDoesNotThrow(() -> plane.performFlight(20));
        assertTrue(plane.getInfo().contains("топливо: 80 л"));
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void repairInvalidatesPreviousInspection(boolean military) {
        Aircraft plane = createAircraft(military, true);
        assertTrue(plane.inspect());
        plane.repair();
        assertThrows(IllegalStateException.class, () -> plane.performFlight(20));
        assertTrue(plane.inspect());
        assertDoesNotThrow(() -> plane.performFlight(20));
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void refuelingAndFlightChangeFuel(boolean military) {
        Aircraft plane = createAircraft(military, true);
        plane.inspect();
        plane.refuel(50);
        assertTrue(plane.getInfo().contains("топливо: 150 л"));
        plane.performFlight(60);
        assertTrue(plane.getInfo().contains("топливо: 90 л"));
        plane.performFlight(90);
        assertTrue(plane.getInfo().contains("топливо: 0 л"));
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void insufficientFuelDoesNotChangeState(boolean military) {
        Aircraft plane = createAircraft(military, true);
        plane.inspect();
        String before = plane.getInfo();
        assertThrows(IllegalStateException.class, () -> plane.performFlight(101));
        assertEquals(before, plane.getInfo());
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void rejectsInvalidFuelAmounts(boolean military) {
        Aircraft plane = createAircraft(military, true);
        plane.inspect();
        String before = plane.getInfo();
        assertThrows(IllegalArgumentException.class, () -> plane.refuel(0));
        assertThrows(IllegalArgumentException.class, () -> plane.refuel(-1));
        assertThrows(IllegalArgumentException.class, () -> plane.refuel(Integer.MAX_VALUE));
        assertThrows(IllegalArgumentException.class, () -> plane.performFlight(0));
        assertThrows(IllegalArgumentException.class, () -> plane.performFlight(-1));
        assertEquals(before, plane.getInfo());
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void renamesAircraftAndRejectsBlankNames(boolean military) {
        Aircraft plane = createAircraft(military, true);
        plane.setName("  Новое имя  ");
        assertTrue(plane.getInfo().contains("самолёт: Новое имя,"));
        String before = plane.getInfo();
        assertThrows(IllegalArgumentException.class, () -> plane.setName(null));
        assertThrows(IllegalArgumentException.class, () -> plane.setName(""));
        assertThrows(IllegalArgumentException.class, () -> plane.setName("   "));
        assertEquals(before, plane.getInfo());
    }
}
