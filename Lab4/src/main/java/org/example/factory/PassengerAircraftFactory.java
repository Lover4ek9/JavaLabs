package org.example.factory;

import org.example.model.PassengerAircraft;

public class PassengerAircraftFactory extends AircraftFactory {
    @Override
    public PassengerAircraft createAircraft(String name, int range, int fuel, int capacity) {
        return new PassengerAircraft(name, range, fuel, capacity);
    }
}
