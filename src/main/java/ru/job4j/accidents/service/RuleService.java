package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleMem;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleMem repository;

    public Set<Rule> getSetRule(Set<String> rIds) {
        return repository.getSetRule(rIds);
    }

    public Rule save(Rule rule) {
        return repository.save(rule);
    }

    public Rule findById(int id) {
        return repository.findById(id);
    }

    public Collection<Rule> getAll() {
        return repository.getAll();
    }
}
