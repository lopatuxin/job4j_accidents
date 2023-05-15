package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.*;

import java.util.Set;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentServiceData accidentSimpleService;
    private final AccidentTypeServiceData accidentTypeSimpleService;
    private final RuleServiceData ruleSimpleService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeSimpleService.getAll());
        model.addAttribute("rules", ruleSimpleService.getAll());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam("type.id") String id,
                       @RequestParam(required = false) Set<String> rIds) {
        accidentSimpleService.save(accident, Integer.parseInt(id), rIds);
        return "redirect:/index";
    }

    @GetMapping("/{id}")
    public String viewEditAccident(Model model, @PathVariable int id) {
        var accidentOptional = accidentSimpleService.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("accident", accidentOptional.get());
        model.addAttribute("types",
                accidentTypeSimpleService.getById(accidentOptional.get().getAccidentType().getId()));
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, @RequestParam("id") int id) {
        accidentSimpleService.update(accident, id);
        return "redirect:/index";
    }
}
