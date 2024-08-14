package com.abiliu.notify.services.impl;

import com.abiliu.notify.models.UserResponseModel;
import com.abiliu.notify.repositories.UserRepository;
import com.abiliu.notify.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  {
    private final UserRepository userRepository;

    // @Override
    // public UserResponseModel findUserById(int id) {
    //     return userRepository.findById(id);
    // }

}
