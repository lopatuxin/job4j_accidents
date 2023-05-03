package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeMem;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AccidentTypeService {

    private final AccidentTypeMem repository;

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
