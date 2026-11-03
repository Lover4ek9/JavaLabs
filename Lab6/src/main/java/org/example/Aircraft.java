package org.example;

public interface Aircraft {
    boolean inspect();

    void repair();

    void performFlight(int fuelNeeded);

    void refuel(int amount);

    String getInfo();

    void setName(String name);
}
