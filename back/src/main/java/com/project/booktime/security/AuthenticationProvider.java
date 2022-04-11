package com.project.booktime.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationProvider
{
    public Authentication authenticate(Authentication authentication);

    public boolean supports(Class<?> authentication);
}
