package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentRepository {
    private final JdbcTemplate jdbc;
    private final AtomicInteger nextId = new AtomicInteger(7);

    private final AccidentTypeJdbcRepository accidentTypeRepository;

    private final RuleJdbcRepository ruleRepository;

    @Override
    public Accident save(Accident accident, int typeId, Set<String> rIds) {
        jdbc.update("insert into accidents (name, text, address, accident_type_id) values(?, ?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accidentTypeRepository.getById(typeId).getId());
        for (String id : rIds) {
            jdbc.update("insert into accidents_rules (accidents_id, rules_id) values(?, ?)",
                    nextId.incrementAndGet(),
                    Integer.parseInt(id));
        }
        return accident;
    }

    @Override
    public Collection<Accident> findAll() {
        return jdbc.query("select * from accidents",
                (resultSet, row) -> new Accident(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("text"),
                        resultSet.getString("address"),
                        accidentTypeRepository.getById(resultSet.getInt("accident_type_id")),
                        ruleRepository.getSetRule(resultSet.getInt("id"))
                ));
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.of(jdbc.query("select * from accidents where id = ?",
                (rs, i) -> new Accident(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("address"),
                        accidentTypeRepository.getById(rs.getInt("accident_type_id")),
                        ruleRepository.getSetRule(rs.getInt("id"))
                ), id).stream()
                .findAny().orElse(new Accident()));
    }

    @Override
    public void update(Accident accident, int id) {
        jdbc.update("update accidents set name = ?, text = ?, address = ? "
                        + "where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                id);
        jdbc.update("DELETE FROM accidents_rules WHERE accidents_id = ?", id);
        for (Rule rule : findById(id).get().getRules()) {
            jdbc.update("INSERT INTO accidents_rules (accidents_id, rules_id) VALUES (?, ?)",
                    id,
                    rule.getId());
        }
    }
}
