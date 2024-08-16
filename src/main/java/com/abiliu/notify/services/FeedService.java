package com.abiliu.notify.services;

import com.abiliu.notify.models.FeedRequestModel;

public interface FeedService {
    void createFeed(FeedRequestModel feedRequestModel, int userId);
}
