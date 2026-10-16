package org.example.model;

public class CargoAircraft extends Aircraft {
    private final int cargoCapacity;

    public CargoAircraft(String name, int flightRange, int fuelConsumption, int cargoCapacity) {
        super(name, flightRange, fuelConsumption);
        if (cargoCapacity <= 0) {
            throw new IllegalArgumentException("Грузоподъёмность должна быть положительной");
        }
        this.cargoCapacity = cargoCapacity;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public String toString() {
        return super.toString() + ", грузоподъёмность " + cargoCapacity + " т";
    }
}
