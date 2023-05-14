package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentRepository {
    private final JdbcTemplate jdbc;
    private final AtomicInteger nextId = new AtomicInteger(7);

    private final AccidentTypeJdbcRepository accidentTypeRepository;

    private final RowMapper<Accident> accidentRowMapper = (resultSet, row) -> {
        Accident accident = new Accident();
        accident.setId(resultSet.getInt("aId"));
        accident.setName(resultSet.getString("aName"));
        accident.setText(resultSet.getString("aText"));
        accident.setAddress("aAddress");
        accident.setAccidentType(new AccidentType(
                resultSet.getInt("tId"),
                resultSet.getString("tName")));
        return accident;
    };

    private final RowMapper<AccidentType> accidentTypeRowMapper = (resultSet, row) -> {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(resultSet.getInt("tId"));
        accidentType.setName(resultSet.getString("tName"));
        return accidentType;
    };

    private final RowMapper<Rule> ruleRowMapper = (resultSet, row) -> {
        Rule rule = new Rule();
        rule.setId(resultSet.getInt("rId"));
        rule.setName(resultSet.getString("rName"));
        return rule;
    };

    private final ResultSetExtractor<Map<Integer, Accident>> extractor = (resultSet) -> {
        Map<Integer, Accident> result = new HashMap<>();
        while (resultSet.next()) {
            Accident accident = new Accident(
                    resultSet.getInt("aId"),
                    resultSet.getString("aName"),
                    resultSet.getString("aText"),
                    resultSet.getString("aAddress"),
                    new AccidentType(
                            resultSet.getInt("tId"),
                            resultSet.getString("tName")),
                    new HashSet<>()
            );
            result.putIfAbsent(accident.getId(), accident);
            result.get(accident.getId()).addRule(new Rule(
                    resultSet.getInt("rId"),
                    resultSet.getString("rName")
            ));
        }
        return result;
    };

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
        Collection<Accident> accidents = jdbc.query(
                "select a.id aId, a.name aName, a.text aText, a.address aAddress, "
                        + "t.id tId, t.name tName, "
                        + "r.id rId, r.name rName "
                        + "from accidents a "
                        + "join accident_type t "
                        + "on a.accident_type_id = t.id "
                        + "join accidents_rules ar on a.id = ar.accidents_id "
                        + "join rules r on r.id = ar.rules_id", extractor).values();
        return accidents.size() > 0 ? new ArrayList<>(accidents) : new ArrayList<>();
    }

    @Override
    public Optional<Accident> findById(int id) {
        Accident accident = jdbc.queryForObject("select a.id aId, "
                + "a.name aName, a.text aText, a.address aAddress, t.id tId, t.name tName "
                + "from accidents a "
                + "left join accident_type t on a.accident_type_id = t.id "
                + "where a.id = ?", accidentRowMapper, id);
        List<Rule> rules = jdbc.query(
                "select r.id rid, r.name rName from accidents a "
                        + "left join accidents_rules ar on a.id = ar.accidents_id "
                        + "left join rules r on r.id = ar.rules_id where a.id = ?",
                ruleRowMapper, id);
        accident.setRules(new HashSet<>(rules));
        return Optional.ofNullable(accident);
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
