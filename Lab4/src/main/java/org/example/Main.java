package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Aircraft;
import org.example.model.Airline;
import org.example.service.AirlineService;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        AirlineService service = new AirlineService();
        Path path = Path.of("aircraft.txt");
        LOGGER.info("Чтение файла {}", path.toAbsolutePath());
        try {
            Airline airline = service.loadFromFile(path);
            System.out.println("\nАвиакомпания: Учебные авиалинии");
            System.out.println("Общая пассажировместимость: "
                    + service.getTotalPassengerCapacity(airline) + " мест");
            System.out.println("Общая грузоподъёмность: "
                    + service.getTotalCargoCapacity(airline) + " т");

            System.out.println("\nСамолёты по возрастанию дальности полёта:");
            for (Aircraft plane : service.sortByFlightRange(airline)) {
                System.out.println(plane);
            }

            System.out.println("\nСамолёты с расходом от 1000 до 2000 л/ч включительно:");
            for (Aircraft plane : service.findByFuelConsumption(airline, 1000, 2000)) {
                System.out.println(plane);
            }
            LOGGER.info("Работа программы завершена");
        } catch (IOException e) {
            LOGGER.error("Не удалось прочитать файл {}", path.toAbsolutePath(), e);
        }
    }
}
