package com.project.booktime.security;

import com.project.booktime.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailService
{
    public UserDetails loadUserByEmailAndPassword (String email, String password) throws UserNotFoundException;
}
