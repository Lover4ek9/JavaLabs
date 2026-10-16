package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Airline {
    private final List<Aircraft> aircraft = new ArrayList<>();

    public void addAircraft(Aircraft plane) {
        aircraft.add(plane);
    }

    public List<Aircraft> getAircraft() {
        return List.copyOf(aircraft);
    }
}
