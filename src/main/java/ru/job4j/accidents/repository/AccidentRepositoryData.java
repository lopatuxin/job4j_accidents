package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.List;

public interface AccidentRepositoryData extends CrudRepository<Accident, Integer> {

    @Query("select distinct a from Accident a join fetch a.rules")
    List<Accident> findAll();

    @Modifying
    @Query("update Accident as a set a.name = ?1, a.text = ?2, a.address = ?3 where a.id = ?4")
    void update(String name, String text, String address, int id);
}
