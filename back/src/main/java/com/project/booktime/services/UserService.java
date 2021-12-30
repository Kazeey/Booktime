package com.project.booktime.services;

import com.project.booktime.model.entity.User;
import com.project.booktime.repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    public User add(User user) {
        return repository.save(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }
}
