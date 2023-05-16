package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class AccidentHibernate implements AccidentRepository {
    private final CrudRepository crudRepository;

    @Override
    public Accident save(Accident accident, int typeId, Set<String> rIds) {
        crudRepository.run(session -> session.save(accident));
        return accident;
    }

    @Override
    public Collection<Accident> findAll() {
        return crudRepository.query("from Accident", Accident.class);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return crudRepository.optional("from Accident where id = :fId",
                Accident.class,
                Map.of("fId", id));
    }

    @Override
    public void update(Accident accident, int id) {
        crudRepository.run(session -> session.update(accident));
    }
}
