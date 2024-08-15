package com.abiliu.notify.services;

import com.abiliu.notify.models.UserRequestModel;
import com.abiliu.notify.models.UserResponseModel;

import java.util.List;

public interface UserService {
    UserResponseModel findUserById(int id);

    UserResponseModel createUser(UserRequestModel userRequestModel);

    List<UserResponseModel> findAllUsers();
}
