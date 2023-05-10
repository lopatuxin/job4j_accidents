package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem implements RuleRepository {

    private final AtomicInteger nextId = new AtomicInteger(1);
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleMem() {
        save(new Rule(1, "Статья. 1"));
        save(new Rule(2, "Статья. 2"));
        save(new Rule(3, "Статья. 3"));
    }

    public Rule save(Rule rule) {
        rule.setId(nextId.incrementAndGet());
        rules.put(rule.getId(), rule);
        return rule;
    }

    public Rule findById(int id) {
        return rules.get(id);
    }

    public Collection<Rule> getAll() {
        return rules.values();
    }

    @Override
    public Set<Rule> getSetRule(int id) {
        return null;
    }

    public Set<Rule> getSetRule(Set<String> rIds) {
        Set<Rule> rules = new HashSet<>();
        for (String id : rIds) {
            rules.add(findById(Integer.parseInt(id)));
        }
        return rules;
    }
}
