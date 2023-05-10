package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleJdbcRepository;
import ru.job4j.accidents.repository.RuleMem;

import java.util.Collection;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleSimpleService implements RuleService {

    private final RuleJdbcRepository repository;

    public Set<Rule> getSetRule(int id) {
        return repository.getSetRule(id);
    }

    @Override
    public Collection<Rule> getAll() {
        return repository.getAll();
    }

}
