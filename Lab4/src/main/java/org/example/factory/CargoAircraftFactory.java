package org.example.factory;

import org.example.model.CargoAircraft;

public class CargoAircraftFactory extends AircraftFactory {
    @Override
    public CargoAircraft createAircraft(String name, int range, int fuel, int capacity) {
        return new CargoAircraft(name, range, fuel, capacity);
    }
}
