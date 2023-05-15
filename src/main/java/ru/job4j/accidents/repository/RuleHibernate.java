package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
@AllArgsConstructor
public class RuleHibernate implements RuleRepository {

    private final SessionFactory sf;

    @Override
    public Set<Rule> getSetRule(int id) {
        return null;
    }

    @Override
    public Collection<Rule> getAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from Rule", Rule.class).list();
        }
    }

    public Rule getById(int id) {
        try (Session session = sf.openSession()) {
            Query<Rule> query = session.createQuery("from Rule where id = :fId", Rule.class);
            query.setParameter("fId", id);
            return query.uniqueResult();
        }
    }

    public Set<Rule> getSetRules(Set<String> rIds) {
        Set<Rule> rules = new HashSet<>();
        for (String id : rIds) {
            rules.add(getById(Integer.parseInt(id)));
        }
        return rules;
    }
}
