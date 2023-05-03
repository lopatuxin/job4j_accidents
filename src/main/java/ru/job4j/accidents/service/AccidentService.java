package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;
    private final AccidentTypeService accidentTypeService;

    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    public Accident save(Accident accident, int accidentId) {
        accident.setAccidentType(accidentTypeService.getById(accidentId));
        return accidentMem.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public boolean update(Accident accident) {
        return accidentMem.update(accident);
    }
}
