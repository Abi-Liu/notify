package com.abiliu.notify.mappers;

import com.abiliu.notify.entities.Profile;
import com.abiliu.notify.models.ProfileModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile profileModelToProfile(ProfileModel profileModel);

    ProfileModel profileToProfileModel(Profile profile);
}
