package org.happypromine.computer_club.repository;

import org.happypromine.computer_club.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    List<User> findByStatus(String status);

}
