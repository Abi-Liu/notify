package com.abiliu.notify.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredentialsModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
