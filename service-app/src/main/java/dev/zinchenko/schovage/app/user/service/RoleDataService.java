package dev.zinchenko.schovage.app.user.service;

import dev.zinchenko.schovage.app.user.entity.Role;
import dev.zinchenko.schovage.app.user.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleDataService {
    private final RoleRepository repository;

    public RoleDataService(RoleRepository repository) {
        this.repository = repository;
    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    public void save(Role role) {
        repository.save(role);
    }

    public Optional<Role> findByName(String name) {
        return repository.findByName(name);
    }
}
