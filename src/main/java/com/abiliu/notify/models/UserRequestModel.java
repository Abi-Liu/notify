package com.abiliu.notify.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestModel {
    private CredentialsModel credentials;
    private ProfileModel profile;
}
