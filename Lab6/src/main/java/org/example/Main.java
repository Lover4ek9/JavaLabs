package org.example;

public class Main {
    public static void main(String[] args) {
        CivilAircraft civil = new CivilAircraft("Лайнер", 100, true, 150);
        MilitaryAircraft military = new MilitaryAircraft("Сокол", 50, false);

        Aircraft[] aircraft = {civil, military};
        for (Aircraft plane : aircraft) {
            System.out.println("\n" + plane.getInfo());
            plane.setName(plane == civil ? "Лайнер-1" : "Сокол-1");
            if (!plane.inspect()) {
                plane.repair();
                plane.inspect();
            }
            plane.refuel(200);
            plane.performFlight(100);
            System.out.println(plane.getInfo());
        }

        System.out.println("\nДополнительные возможности:");
        civil.boardPassengers(80);
        civil.performFlight(100);
        military.performTraining(100);

        System.out.println("\nПример запрета рейса при нехватке топлива:");
        try {
            civil.performFlight(500);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nИтоговая информация:");
        for (Aircraft plane : aircraft) {
            System.out.println(plane.getInfo());
        }
    }
}
