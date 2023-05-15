package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;

public interface AccidentTypeRepositoryData extends CrudRepository<AccidentType, Integer> {

    @Query("from AccidentType")
    List<AccidentType> findAll();
}
