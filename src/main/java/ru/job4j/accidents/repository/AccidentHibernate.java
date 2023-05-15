package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class AccidentHibernate implements AccidentRepository {
    private final SessionFactory sf;
    private final AccidentTypeHibernate accidentTypeHibernate;
    private final RuleHibernate ruleHibernate;

    @Override
    public Accident save(Accident accident, int typeId, Set<String> rIds) {
        accident.setAccidentType(accidentTypeHibernate.getById(typeId));
        accident.setRules(ruleHibernate.getSetRules(rIds));
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(accident);
            session.getTransaction().commit();
        }
        return accident;
    }

    @Override
    public Collection<Accident> findAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from Accident", Accident.class).list();
        }
    }

    @Override
    public Optional<Accident> findById(int id) {
        try (Session session = sf.openSession()) {
            Query<Accident> query = session.createQuery(
                    "from Accident where id = :fId",
                    Accident.class
            );
            query.setParameter("fId", id);
            return Optional.ofNullable(query.uniqueResult());
        }
    }

    @Override
    public void update(Accident accident, int id) {
        Accident oldAccident = findById(id).get();
        accident.setAccidentType(oldAccident.getAccidentType());
        accident.setRules(oldAccident.getRules());
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.merge(accident);
            session.getTransaction().commit();
        }
    }
}
