package com.abiliu.notify.services.impl;

import com.abiliu.notify.entities.User;
import com.abiliu.notify.exceptions.BadRequestException;
import com.abiliu.notify.exceptions.NotFoundException;
import com.abiliu.notify.mappers.UserMapper;
import com.abiliu.notify.models.UserRequestModel;
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
            throw new NotFoundException("User not found");
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
    public UserResponseModel createUser(UserRequestModel userRequestModel) {
        Optional<User> user = userRepository.findByEmail(userRequestModel.getCredentials().getEmail());
        if (user.isPresent()) {
            throw new BadRequestException("User with that email already exists");
        }
        User newUser = userMapper.UserRequestModelToUser(userRequestModel);
        return userMapper.userToResponseModel(userRepository.saveAndFlush(newUser));
    }
}
