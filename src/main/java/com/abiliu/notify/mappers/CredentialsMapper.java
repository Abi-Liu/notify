package com.abiliu.notify.mappers;

import com.abiliu.notify.entities.Credentials;
import com.abiliu.notify.models.CredentialsModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {
    CredentialsModel credentialsToCredentialsModel(Credentials credentials);

    Credentials credentialsModelToCredentials(CredentialsModel credentialsModel);
}
