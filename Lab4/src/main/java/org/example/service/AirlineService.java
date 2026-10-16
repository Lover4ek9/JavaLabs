package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.factory.AircraftFactory;
import org.example.factory.CargoAircraftFactory;
import org.example.factory.PassengerAircraftFactory;
import org.example.model.Aircraft;
import org.example.model.Airline;
import org.example.model.CargoAircraft;
import org.example.model.PassengerAircraft;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AirlineService {
    private static final Logger LOGGER = LogManager.getLogger(AirlineService.class);

    public Airline loadFromFile(Path path) throws IOException {
        Airline airline = new Airline();
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }
            try {
                String[] fields = line.split(";", -1);
                if (fields.length != 5) {
                    throw new IllegalArgumentException("Ожидается 5 полей через ;");
                }
                for (int j = 0; j < fields.length; j++) {
                    fields[j] = fields[j].trim();
                }
                AircraftFactory factory;
                switch (fields[0]) {
                    case "PASSENGER":
                        factory = new PassengerAircraftFactory();
                        break;
                    case "CARGO":
                        factory = new CargoAircraftFactory();
                        break;
                    default:
                        throw new IllegalArgumentException("Неизвестный тип самолёта: " + fields[0]);
                }
                Aircraft plane = factory.createAircraft(fields[1], Integer.parseInt(fields[2]),
                        Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));
                airline.addAircraft(plane);
                LOGGER.debug("Добавлен самолёт: {}", plane);
            } catch (IllegalArgumentException e) {
                LOGGER.warn("Строка {} пропущена: {}. Данные: {}", i + 1, e.getMessage(), line);
            }
        }
        LOGGER.info("Загружено самолётов: {}", airline.getAircraft().size());
        return airline;
    }

    public int getTotalPassengerCapacity(Airline airline) {
        int total = 0;
        for (Aircraft plane : airline.getAircraft()) {
            if (plane instanceof PassengerAircraft passenger) {
                total += passenger.getPassengerCapacity();
            }
        }
        return total;
    }

    public int getTotalCargoCapacity(Airline airline) {
        int total = 0;
        for (Aircraft plane : airline.getAircraft()) {
            if (plane instanceof CargoAircraft cargo) {
                total += cargo.getCargoCapacity();
            }
        }
        return total;
    }

    /** Возвращает отсортированную копию, сохраняя исходный порядок в компании. */
    public List<Aircraft> sortByFlightRange(Airline airline) {
        List<Aircraft> sorted = new ArrayList<>(airline.getAircraft());
        sorted.sort(Comparator.comparingInt(Aircraft::getFlightRange));
        return sorted;
    }

    /** Обе границы диапазона включены. */
    public List<Aircraft> findByFuelConsumption(Airline airline, int min, int max) {
        if (min < 0 || min > max) {
            throw new IllegalArgumentException("Нужен диапазон 0 <= min <= max");
        }
        List<Aircraft> found = new ArrayList<>();
        for (Aircraft plane : airline.getAircraft()) {
            if (plane.getFuelConsumption() >= min && plane.getFuelConsumption() <= max) {
                found.add(plane);
            }
        }
        return found;
    }
}
