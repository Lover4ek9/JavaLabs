package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MilitaryAircraftTest {
    @Test
    void createsAircraftWithInitialInformation() {
        MilitaryAircraft plane = new MilitaryAircraft("Сокол", 100, false);
        assertEquals("Военный самолёт: Сокол, топливо: 100 л, исправен: false, "
                + "техосмотр: false, учебных заданий выполнено: 0", plane.getInfo());
    }

    @Test
    void rejectsInvalidConstructorArguments() {
        assertThrows(IllegalArgumentException.class, () -> new MilitaryAircraft(null, 100, true));
        assertThrows(IllegalArgumentException.class, () -> new MilitaryAircraft(" ", 100, true));
        assertThrows(IllegalArgumentException.class, () -> new MilitaryAircraft("Сокол", -1, true));
    }

    @Test
    void trainingConsumesFuelAndCountsCompletedTasks() {
        MilitaryAircraft plane = new MilitaryAircraft("Сокол", 100, true);
        plane.inspect();
        plane.performTraining(40);
        assertTrue(plane.getInfo().contains("топливо: 60 л"));
        assertTrue(plane.getInfo().contains("учебных заданий выполнено: 1"));
        plane.performTraining(60);
        assertTrue(plane.getInfo().contains("топливо: 0 л"));
        assertTrue(plane.getInfo().contains("учебных заданий выполнено: 2"));
    }

    @Test
    void failedTrainingDoesNotChangeState() {
        MilitaryAircraft plane = new MilitaryAircraft("Сокол", 100, true);
        String before = plane.getInfo();
        assertThrows(IllegalStateException.class, () -> plane.performTraining(20));
        assertEquals(before, plane.getInfo());
        plane.inspect();
        plane.performTraining(20);
        before = plane.getInfo();
        assertThrows(IllegalStateException.class, () -> plane.performTraining(81));
        assertThrows(IllegalArgumentException.class, () -> plane.performTraining(0));
        assertEquals(before, plane.getInfo());
    }
}
