package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentHibernate;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentHbnService implements AccidentService {

    private final AccidentHibernate repository;

    @Override
    public Accident save(Accident accident, int typeId, Set<String> rIds) {
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
        repository.update(accident, id);
    }
}
