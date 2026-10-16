package org.example.model;

public class PassengerAircraft extends Aircraft {
    private final int passengerCapacity;

    public PassengerAircraft(String name, int flightRange, int fuelConsumption, int passengerCapacity) {
        super(name, flightRange, fuelConsumption);
        if (passengerCapacity <= 0) {
            throw new IllegalArgumentException("Количество мест должно быть положительным");
        }
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public String toString() {
        return super.toString() + ", мест " + passengerCapacity;
    }
}
