package com.abiliu.notify.mappers;

import com.abiliu.notify.entities.Feed;
import com.abiliu.notify.models.FeedRequestModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedMapper {
    Feed feedRequestToFeed(FeedRequestModel feedRequestModel);
}
