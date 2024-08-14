package com.abiliu.notify.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class UserResponseModel {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
