package org.example;

public class MilitaryAircraft implements Aircraft {
    private String name;
    private int fuel;
    private boolean serviceable;
    private boolean inspected;
    private int completedTraining;

    public MilitaryAircraft(String name, int fuel, boolean serviceable) {
        setName(name);
        if (fuel < 0) {
            throw new IllegalArgumentException("Топливо не может быть отрицательным");
        }
        this.fuel = fuel;
        this.serviceable = serviceable;
    }

    @Override
    public boolean inspect() {
        inspected = serviceable;
        System.out.println(name + (inspected ? ": техосмотр пройден" : ": требуется ремонт"));
        return inspected;
    }

    @Override
    public void repair() {
        serviceable = true;
        inspected = false;
        System.out.println(name + ": ремонт выполнен");
    }

    @Override
    public void performFlight(int fuelNeeded) {
        if (fuelNeeded <= 0) {
            throw new IllegalArgumentException("Расход топлива на рейс должен быть положительным");
        }
        if (!serviceable || !inspected) {
            throw new IllegalStateException("Рейс запрещён: самолёт должен быть исправен и пройти техосмотр");
        }
        if (fuel < fuelNeeded) {
            throw new IllegalStateException("Недостаточно топлива для рейса");
        }
        fuel -= fuelNeeded;
        System.out.println(name + ": выполнен рейс, осталось топлива " + fuel + " л");
    }

    @Override
    public void refuel(int amount) {
        if (amount <= 0 || amount > Integer.MAX_VALUE - fuel) {
            throw new IllegalArgumentException("Недопустимое количество топлива");
        }
        fuel += amount;
        System.out.println(name + ": заправлено " + amount + " л");
    }

    @Override
    public String getInfo() {
        return "Военный самолёт: " + name + ", топливо: " + fuel + " л"
                + ", исправен: " + serviceable + ", техосмотр: " + inspected
                + ", учебных заданий выполнено: " + completedTraining;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название не должно быть пустым");
        }
        this.name = name.trim();
    }
    
    public void performTraining(int fuelNeeded) {
        performFlight(fuelNeeded);
        completedTraining++;
        System.out.println(name + ": учебное задание выполнено");
    }
}
