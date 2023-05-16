package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepositoryData;

import java.util.Collection;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleServiceData implements RuleService {

    private  final RuleRepositoryData repository;
    private final AccidentServiceData accidentServiceData;
    @Override
    public Set<Rule> getSetRule(int id) {
        return accidentServiceData.findById(id).get().getRules();
    }

    @Override
    public Collection<Rule> getAll() {
        return repository.findAll();
    }

    @Override
    public Rule getById(int id) {
        return repository.findById(id).get();
    }
}
