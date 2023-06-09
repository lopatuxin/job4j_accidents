package ru.job4j.accidents.service;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;

public interface AccidentTypeService {
    AccidentType save(AccidentType accidentType);

    Collection<AccidentType> getAll();

    AccidentType getById(int id);
}
