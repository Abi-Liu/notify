package com.abiliu.notify.services.impl;

import com.abiliu.notify.entities.User;
import com.abiliu.notify.mappers.UserMapper;
import com.abiliu.notify.models.CredentialsModel;
import com.abiliu.notify.models.UserResponseModel;
import com.abiliu.notify.repositories.UserRepository;
import com.abiliu.notify.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private User getUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            // throw new 404 error
        }
        return optionalUser.get();
    }

    @Override
    public UserResponseModel findUserById(int id) {
        User user = getUserById(id);
        return userMapper.userToResponseModel(user);
    }

    @Override
    public List<UserResponseModel> findAllUsers() {
        return userMapper.usersToResponseModels(userRepository.findAll());
    }

    @Override
    public UserResponseModel createUser(CredentialsModel credentials) {
        Optional<User> user = userRepository.findByEmail(credentials.getEmail());
        if (user.isPresent()) {
            // throw new 400 error user already exists
        }
        User newUser = userMapper.credentialsModelToUser(credentials);
        return userMapper.userToResponseModel(userRepository.saveAndFlush(newUser));
    }


}
