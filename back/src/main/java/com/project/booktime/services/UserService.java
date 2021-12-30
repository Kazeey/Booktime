package com.project.booktime.services;

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

    IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public List<UserDTO> findAll() {
        return UserHelper.convertAll(repository.findAll());
    }

    public UserDTO findById(String id) {
        Optional<User> user = repository.findById(id);

        if (user.isPresent()) {
            return UserHelper.convert(user.get());
        } else {
            // throw ...
            return null;
        }
    }

    public UserDTO add(User user) {
        return UserHelper.convert(repository.save(user));
    }

    public void delete(User user) {
        repository.delete(user);
    }
}
