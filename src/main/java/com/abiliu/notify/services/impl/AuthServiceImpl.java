package com.abiliu.notify.services.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    public String hashPassword(String password) {
        return BCrypt.withDefaults().hash(12, password.getBytes()).toString();
    }

    public boolean validatePasswords(String passwordToMatch, String savedPassword) {
        return BCrypt.verifyer().verify(passwordToMatch.getBytes(), savedPassword.getBytes()).verified;
    }
}
