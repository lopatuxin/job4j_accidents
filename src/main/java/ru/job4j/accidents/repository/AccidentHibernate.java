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
    private final AccidentTypeHibernate accidentTypeHibernate;
    private final RuleHibernate ruleHibernate;

    @Override
    public Accident save(Accident accident, int typeId, Set<String> rIds) {
        accident.setAccidentType(accidentTypeHibernate.getById(typeId));
        accident.setRules(ruleHibernate.getSetRules(rIds));
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
        Accident oldAccident = findById(id).get();
        accident.setAccidentType(oldAccident.getAccidentType());
        accident.setRules(oldAccident.getRules());
        crudRepository.run(session -> session.update(accident));
    }
}
