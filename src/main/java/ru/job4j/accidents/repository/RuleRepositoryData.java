package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Set;

public interface RuleRepositoryData extends CrudRepository<Rule, Integer> {

    @Query("from Rule")
    List<Rule> findAll();

    @Query("select r from Rule r where id in :ids")
    Set<Rule> getSetRules(@Param("ids") Set<Integer> rIds);
}
