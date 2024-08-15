package com.abiliu.notify.mappers;

import com.abiliu.notify.entities.User;
import com.abiliu.notify.models.UserRequestModel;
import com.abiliu.notify.models.UserResponseModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseModel userToResponseModel(User user);

    List<UserResponseModel> usersToResponseModels(List<User> users);

    User UserRequestModelToUser(UserRequestModel userRequestModel);
}
