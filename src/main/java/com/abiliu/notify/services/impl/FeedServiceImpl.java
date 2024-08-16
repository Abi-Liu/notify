package com.abiliu.notify.services.impl;

import com.abiliu.notify.entities.Feed;
import com.abiliu.notify.models.FeedRequestModel;
import com.abiliu.notify.repositories.FeedRepository;
import com.abiliu.notify.repositories.UserRepository;
import com.abiliu.notify.services.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;

    @Override
    public void createFeed(FeedRequestModel feedRequestModel) {
        Feed feed = new Feed();
        feed.setUrl(feedRequestModel.getUrl());
        
    }
}
