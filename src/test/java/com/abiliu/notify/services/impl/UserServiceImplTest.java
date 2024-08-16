package com.abiliu.notify.services.impl;


import com.abiliu.notify.entities.Credentials;
import com.abiliu.notify.entities.User;
import com.abiliu.notify.exceptions.BadRequestException;
import com.abiliu.notify.exceptions.NotFoundException;
import com.abiliu.notify.mappers.UserMapper;
import com.abiliu.notify.models.CredentialsModel;
import com.abiliu.notify.models.ProfileModel;
import com.abiliu.notify.models.UserRequestModel;
import com.abiliu.notify.models.UserResponseModel;
import com.abiliu.notify.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
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
    void findUserById_ShouldThrowException_IfUserDoesNotExist() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.findUserById(1));
    }

    @Test
    void findUserById_ShouldReturnUser_IfUserExists() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserResponseModel model = new UserResponseModel();
        when(userMapper.userToResponseModel(user)).thenReturn(model);

        UserResponseModel result = userService.findUserById(1);

        assertNotNull(result);
    }

    @Test
    void findAllUsers_ShouldReturnEmptyList_IfNoUsersExist() {
        List<User> users = new ArrayList<>();
        List<UserResponseModel> usersResponse = new ArrayList<>();

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.usersToResponseModels(users)).thenReturn(usersResponse);

        List<UserResponseModel> result = userService.findAllUsers();
        assert (result.size() == 0);
    }

    @Test
    void findAllUsers_ShouldReturnAListOfUsers() {
        User user1 = new User();
        user1.setId(1);
        User user2 = new User();
        user2.setId(2);
        List<User> users = List.of(user1, user2);
        List<UserResponseModel> usersResponse = List.of(new UserResponseModel(), new UserResponseModel());

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.usersToResponseModels(users)).thenReturn(usersResponse);

        List<UserResponseModel> result = userService.findAllUsers();
        assert (result.size() == 2);
    }

    @Test
    void createUser_ShouldThrowError_IfEmailExists() {
        User existingUser = new User();
        String email = "bob@ross.com";
        Credentials creds = new Credentials(email, "");
        existingUser.setCredentials(creds);

        when(userRepository.findByCredentialsEmail(email)).thenReturn(Optional.of(existingUser));

        CredentialsModel credentials = new CredentialsModel();
        ProfileModel profile = new ProfileModel();
        UserRequestModel request = new UserRequestModel(credentials, profile);
        credentials.setEmail(email);

        assertThrows(BadRequestException.class, () -> userService.createUser(request));
    }

    @Test
    void createUser_ShouldCreateUser_IfEmailDoesNotExist() {
        String email = "bob@ross.com";
        String password = "BOB";
        Credentials creds = new Credentials(email, password);
        User user = new User();
        user.setCredentials(creds);
        CredentialsModel credentials = new CredentialsModel();
        credentials.setEmail(email);


        UserResponseModel response = new UserResponseModel();

        assertNotNull(response);
    }
}
