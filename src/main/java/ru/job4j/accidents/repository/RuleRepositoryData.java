package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.List;

public interface RuleRepositoryData extends CrudRepository<Rule, Integer> {

    @Query("from Rule")
    List<Rule> findAll();
}
