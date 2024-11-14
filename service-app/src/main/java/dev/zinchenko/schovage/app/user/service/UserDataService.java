package dev.zinchenko.schovage.app.user.service;

import dev.zinchenko.schovage.app.user.entity.User;
import dev.zinchenko.schovage.app.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDataService {
    private final UserRepository repository;

    public UserDataService(UserRepository repository) {
        this.repository = repository;
    }

    public long count() {
        return repository.count();
    }

    public void save(User user) {
        repository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
