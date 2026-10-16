package org.example.model;

public abstract class Aircraft {
    private final String name;
    private final int flightRange;
    private final int fuelConsumption;

    protected Aircraft(String name, int flightRange, int fuelConsumption) {
        if (name == null || name.isBlank() || flightRange <= 0 || fuelConsumption <= 0) {
            throw new IllegalArgumentException("Нужны название, положительная дальность и расход");
        }
        this.name = name;
        this.flightRange = flightRange;
        this.fuelConsumption = fuelConsumption;
    }

    public String getName() {
        return name;
    }

    public int getFlightRange() {
        return flightRange;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public String toString() {
        return name + ": дальность " + flightRange + " км, расход " + fuelConsumption + " л/ч";
    }
}
