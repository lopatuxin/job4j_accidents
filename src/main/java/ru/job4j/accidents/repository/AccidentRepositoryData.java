package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accidents.model.Accident;

import java.util.List;

public interface AccidentRepositoryData extends CrudRepository<Accident, Integer> {

    @Query("select distinct a from Accident a join fetch a.rules")
    List<Accident> findAll();

    @Modifying
    @Query("update Accident as a set a.name = :name, a.text = :text, a.address = :address where a.id = :id")
    void update(
            @Param("name") String name,
            @Param("text") String text,
            @Param("address") String address,
            @Param("id") int id);
}
