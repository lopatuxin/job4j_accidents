package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentHibernate;
import ru.job4j.accidents.repository.AccidentTypeHibernate;
import ru.job4j.accidents.repository.RuleHibernate;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentHbnService implements AccidentService {

    private final AccidentHibernate repository;
    private final AccidentTypeHibernate accidentTypeHibernate;
    private final RuleHibernate ruleHibernate;

    @Override
    public Accident save(Accident accident, int typeId, Set<String> rIds) {
        accident.setAccidentType(accidentTypeHibernate.getById(typeId));
        accident.setRules(ruleHibernate.getSetRules(rIds));
        return repository.save(accident, typeId, rIds);
    }

    @Override
    public Collection<Accident> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void update(Accident accident, int id) {
        Accident oldAccident = findById(id).get();
        accident.setAccidentType(oldAccident.getAccidentType());
        accident.setRules(oldAccident.getRules());
        repository.update(accident, id);
    }
}
