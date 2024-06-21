package org.happypromine.computer_club.controller;

import org.happypromine.computer_club.model.Payment;
import org.happypromine.computer_club.service.PaymentService;
import org.happypromine.computer_club.service.SessionService;
import org.happypromine.computer_club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public String getAllPayments(Model model) {
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "payments";
    }

    @GetMapping("/add")
    public String showAddPaymentForm(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("sessions", sessionService.getAllSessions());
        return "add-payment";
    }

    @PostMapping
    public String createPayment(@ModelAttribute Payment payment) {
        payment.setDateTime(Timestamp.from(Instant.now()));
        paymentService.createPayment(payment);
        return "redirect:/payments";
    }

    @GetMapping("/edit/{id}")
    public String showEditPaymentForm(@PathVariable Long id, Model model) {
        Payment payment = paymentService.getPaymentById(id);

        model.addAttribute("payment", payment);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("sessions", sessionService.getAllSessions());
        return "edit-payment";
    }

    @PostMapping("/update/{id}")
    public String updatePayment(@PathVariable Long id, @ModelAttribute Payment payment) {
        Payment existingPayment = paymentService.getPaymentById(id);
        payment.setId(id);
        payment.setDateTime(existingPayment.getDateTime());
        paymentService.updatePayment(payment);
        return "redirect:/payments";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "redirect:/payments";
    }

}
