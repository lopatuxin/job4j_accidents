package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;
import ru.job4j.accidents.repository.AccidentTypeMem;
import ru.job4j.accidents.repository.RuleMem;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;
    private final AccidentTypeMem accidentTypeMem;
    private final RuleMem ruleMem;

    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    public Accident save(Accident accident, int accidentId, Set<String> rIds) {
        accident.setAccidentType(accidentTypeMem.getById(accidentId));
        accident.setRules(ruleMem.getSetRule(rIds));
        return accidentMem.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public boolean update(Accident accident) {
        return accidentMem.update(accident);
    }
}
