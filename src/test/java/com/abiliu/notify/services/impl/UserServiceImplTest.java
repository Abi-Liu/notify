package com.abiliu.notify.services.impl;


import com.abiliu.notify.entities.User;
import com.abiliu.notify.exceptions.NotFoundException;
import com.abiliu.notify.mappers.UserMapper;
import com.abiliu.notify.models.UserResponseModel;
import com.abiliu.notify.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUserById_ShouldThrowException_IfUserDoesNotExist() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.findUserById(1));
    }

    @Test
    void getUserById_ShouldReturnUser_IfUserExists() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserResponseModel model = new UserResponseModel();
        when(userMapper.userToResponseModel(user)).thenReturn(model);

        UserResponseModel result = userService.findUserById(1);

        assertNotNull(result);
    }
}
