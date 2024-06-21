package org.happypromine.computer_club.service;

import org.happypromine.computer_club.model.Payment;
import org.happypromine.computer_club.repository.PaymentRepository;
import org.happypromine.computer_club.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, SessionRepository sessionRepository) {
        this.paymentRepository = paymentRepository;
        this.sessionRepository = sessionRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
