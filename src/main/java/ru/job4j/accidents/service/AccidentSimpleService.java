package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.*;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentSimpleService implements AccidentService {
    private final AccidentJdbcTemplate accidentRepository;

    public Collection<Accident> findAll() {
        return accidentRepository.findAll();
    }

    public Accident save(Accident accident, int accidentId, Set<String> rIds) {
        return accidentRepository.save(accident, accidentId, rIds);
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public void update(Accident accident, int id) {
        accidentRepository.update(accident, id);
    }
}
