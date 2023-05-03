package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {

    private final AtomicInteger nextId = new AtomicInteger(1);
    private final Map<Integer, AccidentType> accidentsType = new ConcurrentHashMap<>();

    public AccidentTypeMem() {
        save(new AccidentType(1, "Две машины"));
        save(new AccidentType(2, "Машина и человек"));
        save(new AccidentType(3, "Машина и велосипед"));
    }

    public AccidentType save(AccidentType accidentType) {
        accidentType.setId(nextId.incrementAndGet());
        accidentsType.put(accidentType.getId(), accidentType);
        return accidentType;
    }

    public Collection<AccidentType> getAll() {
        return accidentsType.values();
    }

    public AccidentType getById(int id) {
        return accidentsType.get(id);
    }
}
