package com.lin.microservice.auth.config;

import org.springframework.security.crypto.password.PasswordEncoder;

// As the NoOpPasswordEncoder is now deprecated my solution (for now) is to have a custom encoder
public class Oauth2ClientPasswordEncoder implements PasswordEncoder {

    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }

    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }

    private static final PasswordEncoder INSTANCE = new Oauth2ClientPasswordEncoder();

    private Oauth2ClientPasswordEncoder() {
    }
}