package org.happypromine.computer_club.service;

import org.happypromine.computer_club.model.Computer;
import org.happypromine.computer_club.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {
    private final ComputerRepository computerRepository;

    @Autowired
    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }

    public Computer getComputerById(Long id) {
        return computerRepository.findById(id).orElse(null);
    }

    public List<Computer> getComputersByAvailability(String availability) {
        return computerRepository.findByAvailability(availability);
    }
    public Computer createComputer(Computer computer) {
        return computerRepository.save(computer);
    }

    public Computer updateComputer(Computer computer) {
        return computerRepository.save(computer);
    }

    public void deleteComputer(Long id) {
        computerRepository.deleteById(id);
    }

}
