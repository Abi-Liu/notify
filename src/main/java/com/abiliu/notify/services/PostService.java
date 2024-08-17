package com.abiliu.notify.services;

import java.time.Duration;

public interface PostService {
    void startScraping(int numPosts, Duration duration) throws InterruptedException;
}
