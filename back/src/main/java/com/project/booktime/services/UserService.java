package com.project.booktime.services;

import com.project.booktime.exception.UserNotFoundException;
import com.project.booktime.model.dto.BookDTO;
import com.project.booktime.model.dto.SignUpDTO;
import com.project.booktime.model.dto.UserDTO;
import com.project.booktime.model.entity.User;
import com.project.booktime.model.helper.UserHelper;
import com.project.booktime.repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    private final IUserRepository repository;
    private final BookService bookService;

    public UserService(IUserRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    public UserDTO findMe(String id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) throw new UserNotFoundException();

        return UserHelper.convert(user.get());
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

    public UserDTO logIn(String email, String password) {
        Optional<User> user = repository.logIn(email, password);

        if (user.isEmpty()) throw new UserNotFoundException();

        return UserHelper.convert(user.get());
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

    public UserDTO changeAccount (User user) {
        User notUpdatedUser = repository.findByEmail(user.getEmail());

        if (notUpdatedUser == null) throw new UserNotFoundException();

        notUpdatedUser.setStatus(user.getStatus());

        User updatedUser = repository.save(notUpdatedUser);

        return UserHelper.convert(updatedUser);
    }

    public void delete(String id) {
        Optional<User> optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) throw new UserNotFoundException();

        repository.delete(optionalUser.get());
    }
}
