package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate implements AccidentTypeRepository {

    private final CrudRepository repository;

    @Override
    public AccidentType save(AccidentType accidentType) {
        repository.run(session -> session.save(accidentType));
        return accidentType;
    }

    @Override
    public Collection<AccidentType> getAll() {
        return repository.query("from AccidentType", AccidentType.class);
    }

    @Override
    public AccidentType getById(int id) {
        return repository.optional("from AccidentType where id = :fId",
                AccidentType.class,
                Map.of("fId", id)).get();
    }
}
