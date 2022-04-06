package com.project.booktime.controllers;

import com.project.booktime.exception.UserNotFoundException;
import com.project.booktime.model.dto.UserDTO;
import com.project.booktime.model.entity.User;
import com.project.booktime.services.MailService;
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

    @PostMapping("/connect")
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

    @GetMapping("/sendMail")
    public void sendMail()
    {
        try {
            MailService test = new MailService();
            test.sendMessage("quentinpeze@hotmail.fr", "test", "text");
        }
        catch (UserNotFoundException exception)
        {
            System.out.println("nop");
        }
    }
}
