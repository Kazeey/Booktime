package com.project.booktime.services;

import com.project.booktime.exception.UserNotFoundException;
import com.project.booktime.model.dto.UserDTO;
import com.project.booktime.model.entity.User;
import com.project.booktime.model.helper.UserHelper;
import com.project.booktime.repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public List<UserDTO> findAll() {
        List<User> userList = repository.findAll();

        return UserHelper.convertAll(userList);
    }

    public UserDTO findById(String id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) throw new UserNotFoundException();

        return UserHelper.convert(user.get());
    }

    public UserDTO findByEmailAndPassword(User user) {
        User userFounded = repository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (userFounded == null) throw new UserNotFoundException();

        return UserHelper.convert(userFounded);
    }

    public UserDTO add(User user) {
        User createdUser = repository.insert(user);

        return UserHelper.convert(createdUser);
    }

    public UserDTO update(String id, User user) {
        Optional<User> optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) throw new UserNotFoundException();

        User updatedUser = repository.save(user);

        return UserHelper.convert(updatedUser);
    }

    public void delete(String id) {
        Optional<User> optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) throw new UserNotFoundException();

        repository.delete(optionalUser.get());
    }
}
