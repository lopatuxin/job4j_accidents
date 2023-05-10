package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentServiceJdbc;

@Controller
public class IndexController {
    private final AccidentService accidentService;

    public IndexController(AccidentServiceJdbc accidentServiceJdbc) {
        this.accidentService = accidentServiceJdbc;
    }

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
