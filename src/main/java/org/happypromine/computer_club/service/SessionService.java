package org.happypromine.computer_club.service;

import org.happypromine.computer_club.model.Session;
import org.happypromine.computer_club.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
 private final SessionRepository sessionRepository;

 @Autowired
    public SessionService(SessionRepository sessionRepository) {
     this.sessionRepository = sessionRepository;
 }

 public List<Session> getAllSessions() {
     return sessionRepository.findAll();
 }

 public List<Session> getSessionsByUserId(Long userId) {
        return sessionRepository.findByUserId(userId);
 }

 public Session getSessionById(Long id) {
     return sessionRepository.findById(id).orElse(null);
 }

 public Session createSession(Session session) {
     return sessionRepository.save(session);
 }

 public Session updateSession(Session session) {
     return sessionRepository.save(session);
 }

 public void deleteSession(Long id) {
     sessionRepository.deleteById(id);
 }

}
