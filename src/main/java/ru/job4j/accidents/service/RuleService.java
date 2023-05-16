package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Set;

public interface RuleService {

    Set<Rule> getSetRule(int id);

    Collection<Rule> getAll();

    Rule getById(int id);
}
