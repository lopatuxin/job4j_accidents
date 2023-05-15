package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepositoryData;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AccidentTypeServiceData implements AccidentTypeService {

    private final AccidentTypeRepositoryData repository;

    @Override
    public AccidentType save(AccidentType accidentType) {
        return repository.save(accidentType);
    }

    @Override
    public Collection<AccidentType> getAll() {
        return repository.findAll();
    }

    @Override
    public AccidentType getById(int id) {
        return repository.findById(id).get();
    }
}
