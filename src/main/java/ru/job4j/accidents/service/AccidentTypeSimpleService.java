package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeHibernate;
import ru.job4j.accidents.repository.AccidentTypeJdbcRepository;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AccidentTypeSimpleService implements AccidentTypeService {

    private final AccidentTypeHibernate repository;

    public AccidentType save(AccidentType accidentType) {

        return repository.save(accidentType);
    }

    public Collection<AccidentType> getAll() {
        return repository.getAll();
    }

    public AccidentType getById(int id) {
        return repository.getById(id);
    }
}
