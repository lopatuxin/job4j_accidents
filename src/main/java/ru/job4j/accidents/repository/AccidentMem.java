package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentRepository {
    private static final AccidentMem INSTANCE = new AccidentMem();

    private final AtomicInteger nextId = new AtomicInteger(1);
    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        save(new Accident(0, "test", "test", "test", new AccidentType(1, "test"),
                Set.of(new Rule(1, "test"))));
        save(new Accident(0, "test1", "test1", "test1", new AccidentType(2, "test1"),
                Set.of(new Rule(2, "test1"))));
        save(new Accident(0, "test2", "test2", "test2", new AccidentType(3, "test2"),
                Set.of(new Rule(3, "test2"))));
    }

    public static AccidentMem getInstance() {
        return INSTANCE;
    }

    public Accident save(Accident accident) {
        accident.setId(nextId.incrementAndGet());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public Accident save(Accident accident, int typeId, Set<String> rIds) {
        return null;
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public void update(Accident accident, int id) {
        accidents.computeIfPresent(id,
                (idOld, oldAccident) ->
                new Accident(oldAccident.getId(),
                        accident.getName(),
                        accident.getText(),
                        accident.getAddress(),
                        accident.getAccidentType(),
                        accident.getRules()));
    }
}
