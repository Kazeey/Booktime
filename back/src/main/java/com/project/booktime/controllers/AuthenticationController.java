package com.project.booktime.controllers;

import com.project.booktime.model.dto.UserDTO;
import com.project.booktime.model.entity.User;
import com.project.booktime.security.JwtTokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController
{
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil)
    {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid User user)
    {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(), user.getPassword()
                    )
            );

            User userPrincipal = (User) authenticate.getPrincipal();

            return (ResponseEntity<?>) ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateToken(userPrincipal))
                    .body(new UserDTO(
                            userPrincipal.getId(),
                            userPrincipal.getPseudo(),
                            userPrincipal.getName(),
                            userPrincipal.getFirstName(),
                            userPrincipal.getEmail(),
                            userPrincipal.getPassword(),
                            userPrincipal.getBirthday(),
                            userPrincipal.getBase64(),
                            userPrincipal.getStatus()
                        )
                    );
        }
        catch (BadCredentialsException e)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
