package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbcRepository implements AccidentTypeRepository {

    private final JdbcTemplate jdbc;

    @Override
    public AccidentType save(AccidentType accidentType) {
        jdbc.update("insert into accident_type(name) values(?)", accidentType.getName());
        return accidentType;
    }

    @Override
    public Collection<AccidentType> getAll() {
        return jdbc.query("select * from accident_type",
                (resultSet, row) -> new AccidentType(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
    }

    @Override
    public AccidentType getById(int id) {
        return jdbc.query("select * from accident_type where id = ?",
                (resultSet, i) -> new AccidentType(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ), id).stream().findAny().orElse(new AccidentType());
    }
}
