package org.example.factory;

import org.example.model.Aircraft;

public abstract class AircraftFactory {
    public abstract Aircraft createAircraft(String name, int range, int fuel, int capacity);
}
