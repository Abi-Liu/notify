package com.abiliu.notify.services;

import com.abiliu.notify.models.UserResponseModel;
import org.springframework.stereotype.Service;

public interface UserService {
    UserResponseModel findUserById(int id);
}
