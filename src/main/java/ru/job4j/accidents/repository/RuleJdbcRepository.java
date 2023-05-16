package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
@AllArgsConstructor
public class RuleJdbcRepository implements RuleRepository {

    private final JdbcTemplate jdbc;

    @Override
    public Set<Rule> getSetRule(int id) {
        return new HashSet<Rule>(
                jdbc.query("select * from rules join accidents_rules "
                        + "on (rules.id = accidents_rules.rules_id) "
                        + "where accidents_rules.accidents_id = ?", new BeanPropertyRowMapper<>(Rule.class), id)
        );
    }

    @Override
    public Collection<Rule> getAll() {
        return jdbc.query("select * from rules",
                (resultSet, i) -> new Rule(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
    }

    @Override
    public Rule getById(int id) {
        return (Rule) jdbc.queryForObject(
                "select * from rules where id = ?", new BeanPropertyRowMapper(Rule.class), id);
    }
}
