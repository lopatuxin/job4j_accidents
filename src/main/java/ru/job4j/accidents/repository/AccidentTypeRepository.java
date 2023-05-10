package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;

public interface AccidentTypeRepository {

    AccidentType save(AccidentType accidentType);

    Collection<AccidentType> getAll();

    AccidentType getById(int id);
}
