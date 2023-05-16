package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentRepositoryData;
import ru.job4j.accidents.repository.AccidentTypeRepositoryData;
import ru.job4j.accidents.repository.RuleRepositoryData;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentServiceData implements AccidentService {

    private final AccidentRepositoryData accidentRepositoryData;
    private final AccidentTypeRepositoryData accidentTypeRepositoryData;
    private final RuleRepositoryData ruleRepositoryData;

    public Set<Integer> getIds(Set<String> rIds) {
        Set<Integer> result = new HashSet<>();
        for (String id : rIds) {
            result.add(Integer.parseInt(id));
        }
        return result;
    }

    @Override
    public Accident save(Accident accident, int typeId, Set<String> rIds) {
        accident.setAccidentType(accidentTypeRepositoryData.findById(typeId).get());
        accident.setRules(ruleRepositoryData.getSetRules(getIds(rIds)));
        return accidentRepositoryData.save(accident);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentRepositoryData.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepositoryData.findById(id);
    }

    @Override
    public void update(Accident accident, int id) {
        accidentRepositoryData.update(
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                id);
    }
}
