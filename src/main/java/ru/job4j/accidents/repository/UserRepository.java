package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accidents.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where username = :name")
    User getByName(@Param("name") String username);
}
