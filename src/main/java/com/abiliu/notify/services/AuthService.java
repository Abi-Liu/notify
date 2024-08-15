package com.abiliu.notify.services;

import com.abiliu.notify.models.CredentialsModel;
import com.abiliu.notify.models.UserResponseModel;

public interface AuthService {
    String hashPassword(String password);

    boolean validatePassword(String password);

    UserResponseModel login(CredentialsModel credentials);
}
