package com.abiliu.notify.services.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.abiliu.notify.entities.User;
import com.abiliu.notify.exceptions.NotFoundException;
import com.abiliu.notify.exceptions.UnauthorizedException;
import com.abiliu.notify.mappers.UserMapper;
import com.abiliu.notify.models.CredentialsModel;
import com.abiliu.notify.models.UserResponseModel;
import com.abiliu.notify.repositories.UserRepository;
import com.abiliu.notify.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private BCrypt.Hasher hasher = BCrypt.withDefaults();
    private BCrypt.Verifyer verifyer = BCrypt.verifyer();

    private boolean validatePassword(String passwordToMatch, String savedPassword) {
        return verifyer.verify(passwordToMatch.toCharArray(), savedPassword).verified;
    }

    @Override
    public String hashPassword(String password) {
        return hasher.hashToString(12, password.toCharArray());
    }

    @Override
    public UserResponseModel login(CredentialsModel credentials) {
        Optional<User> optionalUser = userRepository.findByCredentialsEmail(credentials.getEmail());
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("No account registered to email: " + credentials.getEmail());
        }

        User user = optionalUser.get();

        if (!validatePassword(credentials.getPassword(), user.getCredentials().getPassword())) {
            throw new UnauthorizedException("Wrong password");
        }

        return userMapper.userToResponseModel(user);
    }
}
