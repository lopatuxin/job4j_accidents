package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
@AllArgsConstructor
public class RuleHibernate implements RuleRepository {

    private final CrudRepository repository;

    @Override
    public Set<Rule> getSetRule(int id) {
        return null;
    }

    @Override
    public Collection<Rule> getAll() {
        return repository.query("from Rule", Rule.class);
    }

    public Rule getById(int id) {
        return repository.optional("from Rule where id = :fId",
                Rule.class,
                Map.of("fId", id)).get();
    }

    public Set<Rule> getSetRules(Set<String> rIds) {
        Set<Rule> rules = new HashSet<>();
        for (String id : rIds) {
            rules.add(getById(Integer.parseInt(id)));
        }
        return rules;
    }
}
