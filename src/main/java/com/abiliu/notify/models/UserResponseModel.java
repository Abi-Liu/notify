package com.abiliu.notify.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel {
    private int id;
    private ProfileModel profile;
    private CredentialsModel credentials;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
