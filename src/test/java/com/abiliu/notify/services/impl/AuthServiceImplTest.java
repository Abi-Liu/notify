package com.abiliu.notify.services.impl;

import com.abiliu.notify.entities.Credentials;
import com.abiliu.notify.entities.User;
import com.abiliu.notify.mappers.UserMapper;
import com.abiliu.notify.models.CredentialsModel;
import com.abiliu.notify.models.UserResponseModel;
import com.abiliu.notify.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void hashPassword() {
        String password = "Password123!";
        String hashed = authService.hashPassword(password);

        assert (!hashed.equals(password));
    }

    @Test
    void loginValidEmail() {
        String password = "Password123!";
        String email = "bob@bob.com";
        String hashed = authService.hashPassword(password);

        User user = new User();
        user.setCredentials(new Credentials(email, hashed));

        when(userRepository.findByCredentialsEmail(user.getCredentials().getEmail())).thenReturn(Optional.of(user));
        UserResponseModel response = new UserResponseModel();
        when(userMapper.userToResponseModel(user)).thenReturn(response);

        CredentialsModel request = new CredentialsModel(email, password);
        UserResponseModel result = authService.login(request);

        assertNotNull(result);
    }
}