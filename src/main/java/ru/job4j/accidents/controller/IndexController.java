package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.AccidentHbnService;
import ru.job4j.accidents.service.AccidentService;

import javax.transaction.Transactional;

@Controller
public class IndexController {
    private final AccidentService accidentService;

    public IndexController(AccidentHbnService accidentHibernate) {
        this.accidentService = accidentHibernate;
    }

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
