package org.happypromine.computer_club.repository;

import org.happypromine.computer_club.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer,Long> {
    List<Computer> findByAvailability(String availability);
}
