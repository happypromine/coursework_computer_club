package org.happypromine.computer_club.controller;

import org.happypromine.computer_club.model.Computer;
import org.happypromine.computer_club.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/computers")
public class ComputerController {
    private final ComputerService computerService;

    @Autowired
    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping
    public String getAllComputers(Model model) {
        List<Computer> computers = computerService.getAllComputers();
        model.addAttribute("computers", computers);
        return "computers";
    }

    @GetMapping("/add")
    public String showAddComputerForm(Model model) {
        model.addAttribute("computer", new Computer());
        return "add-computer";
    }

    @PostMapping
    public String createComputer(@ModelAttribute Computer computer) {
        computerService.createComputer(computer);
        return "redirect:/computers";
    }

    @GetMapping("/edit/{id}")
    public String showEditComputerForm(@PathVariable Long id, Model model) {
        Computer computer = computerService.getComputerById(id);
        model.addAttribute("computer", computer);
        return "edit-computer";
    }

    @PostMapping("/update/{id}")
    public String updateComputer(@PathVariable Long id, @ModelAttribute Computer computer) {
        computer.setId(id);
        computerService.updateComputer(computer);
        return "redirect:/computers";
    }

    @GetMapping("/delete/{id}")
    public String deleteComputer(@PathVariable Long id) {
        computerService.deleteComputer(id);
        return "redirect:/computers";
    }


}
