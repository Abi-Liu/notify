package com.abiliu.notify.services;

import com.abiliu.notify.models.CredentialsModel;
import com.abiliu.notify.models.UserResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserResponseModel findUserById(int id);
    UserResponseModel createUser(CredentialsModel credentials);
    List<UserResponseModel> findAllUsers();
}
