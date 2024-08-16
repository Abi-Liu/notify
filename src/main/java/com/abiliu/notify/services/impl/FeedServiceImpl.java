package com.abiliu.notify.services.impl;

import com.abiliu.notify.entities.Feed;
import com.abiliu.notify.entities.User;
import com.abiliu.notify.mappers.FeedMapper;
import com.abiliu.notify.models.FeedRequestModel;
import com.abiliu.notify.repositories.FeedRepository;
import com.abiliu.notify.repositories.UserRepository;
import com.abiliu.notify.services.FeedService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final FeedMapper feedMapper;

    @Override
    @Transactional
    public void createFeed(FeedRequestModel feedRequestModel, int userId) {
        Optional<Feed> optionalFeed = feedRepository.findByUrl(feedRequestModel.getUrl());

        // we can be confident the user exists, because they are authenticated
        User user = userRepository.findById(userId).orElseThrow();

        if (optionalFeed.isPresent()) {
            // if the feed url already exists, just subscribe the user to that feed
            Feed feed = optionalFeed.get();
            if (!user.getFollowedFeeds().contains(feed)) {
                user.getFollowedFeeds().add(optionalFeed.get());
            }
            return;
        }


        Feed feed = feedMapper.feedRequestToFeed(feedRequestModel);
        user.getFollowedFeeds().add(feed);
        userRepository.saveAndFlush(user);
    }
}
