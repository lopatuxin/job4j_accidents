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
    @Override
    public Set<Rule> getSetRule(int id) {
        return null;
    }

    @Override
    public Collection<Rule> getAll() {
        return repository.findAll();
    }
}
