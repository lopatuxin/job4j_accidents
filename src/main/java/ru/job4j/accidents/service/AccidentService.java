package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface AccidentService {

    Accident save(Accident accident, int typeId, Set<String> rIds);

    Collection<Accident> findAll();

    Optional<Accident> findById(int id);

    void update(Accident accident, int id);
}
