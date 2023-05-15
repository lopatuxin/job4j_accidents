package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate implements AccidentTypeRepository {
    private final SessionFactory sf;

    @Override
    public AccidentType save(AccidentType accidentType) {
        try (Session session = sf.openSession()) {
            session.save(accidentType);
        }
        return accidentType;
    }

    @Override
    public Collection<AccidentType> getAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from AccidentType", AccidentType.class).list();
        }
    }

    @Override
    public AccidentType getById(int id) {
        try (Session session = sf.openSession()) {
            Query<AccidentType> query = session.createQuery("from AccidentType where id = :fId",
                    AccidentType.class);
            query.setParameter("fId", id);
            return query.uniqueResult();
        }
    }
}
