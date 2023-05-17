package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleHibernate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleSimpleService implements RuleService {

    private final RuleHibernate repository;

    public Set<Rule> getSetRule(int id) {
        return repository.getSetRule(id);
    }

    @Override
    public Collection<Rule> getAll() {
        return repository.getAll();
    }

    @Override
    public Rule getById(int id) {
        return repository.getById(id);
    }
}
