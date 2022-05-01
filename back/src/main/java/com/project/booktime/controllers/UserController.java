package com.project.booktime.controllers;

import com.project.booktime.exception.UserNotFoundException;
import com.project.booktime.model.dto.LogInDTO;
import com.project.booktime.model.dto.SignUpDTO;
import com.project.booktime.model.dto.UserDTO;
import com.project.booktime.model.entity.User;
import com.project.booktime.model.helper.SignUpHelper;
import com.project.booktime.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me/{id}")
    public ResponseEntity<UserDTO> findMe(@PathVariable("id") String id) {
        try {
            UserDTO userDTO = userService.findMe(id);

            return ResponseEntity.ok().body(userDTO);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO userDTO = userService.add(SignUpHelper.signUpDTOToUser(signUpDTO));

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> userDTOList = userService.findAll();

        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/findBy/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") String id) {
        try {
            UserDTO userDTO = userService.findById(id);

            return ResponseEntity.ok().body(userDTO);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/logIn")
    public ResponseEntity<UserDTO> findById(@RequestBody LogInDTO logInDTO) {
        try {
            UserDTO userDTO = userService.logIn(logInDTO.getEmail(), logInDTO.getPassword());

            return ResponseEntity.ok().body(userDTO);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> add(@RequestBody User user) {
        UserDTO userDTO = userService.add(user);

        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") String id, @RequestBody User user) {
        try {
            UserDTO userDTO = userService.update(id, user);

            return ResponseEntity.ok().body(userDTO);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            userService.delete(id);

            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> connect(@RequestBody User user) {
        try {
            UserDTO userDTO = userService.findByEmailAndPassword(user);

            return ResponseEntity.ok().body(userDTO);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/changeAccount")
    public ResponseEntity<UserDTO> changeAccount(@RequestBody User user) {
        try {
            UserDTO userDTO = userService.changeAccount(user);

            return ResponseEntity.ok().body(userDTO);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
