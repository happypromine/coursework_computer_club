package org.happypromine.computer_club.controller;

import org.happypromine.computer_club.model.Session;
import org.happypromine.computer_club.model.User;
import org.happypromine.computer_club.service.ComputerService;
import org.happypromine.computer_club.service.SessionService;
import org.happypromine.computer_club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sessions")
public class SessionController {
    private final SessionService sessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ComputerService computerService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String getAllSessions(Model model) {
        List<Session> sessions = sessionService.getAllSessions();
        model.addAttribute("sessions", sessions);
        return "sessions";
    }

    @GetMapping("/add")
    public String showAddSessionForm(Model model) {
        List<User> users = new ArrayList<>();
        users.addAll(userService.getUsersByStatus("Активный"));
        users.addAll(userService.getUsersByStatus("ВИП"));
        model.addAttribute("sessionData", new Session());
        model.addAttribute("users", users);
        model.addAttribute("computers", computerService.getComputersByAvailability("Доступен"));
        return "add-session";
    }

    @PostMapping
    public String createSession(@ModelAttribute Session session) {
        sessionService.createSession(session);
        return "redirect:/sessions";
    }

    @GetMapping("/edit/{id}")
    public String showEditSessionForm(@PathVariable Long id, Model model) {
        Session session = sessionService.getSessionById(id);

        List<User> users = new ArrayList<>();
        users.addAll(userService.getUsersByStatus("Активный"));
        users.addAll(userService.getUsersByStatus("ВИП"));

        model.addAttribute("sessionData", session);
        model.addAttribute("users", users);
        model.addAttribute("computers", computerService.getComputersByAvailability("Доступен"));
        return "edit-session";
    }

    @PostMapping("/update/{id}")
    public String updateSession(@PathVariable Long id, @ModelAttribute Session session) {
        session.setId(id);

        sessionService.updateSession(session);
        return "redirect:/sessions";
    }

    @GetMapping("/delete/{id}")
    public String deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return "redirect:/sessions";
    }

    @GetMapping("/user-sessions")
    @ResponseBody
    public List<Session> getUserSessions(@RequestParam Long userId) {
        return sessionService.getSessionsByUserId(userId);
    }
}
