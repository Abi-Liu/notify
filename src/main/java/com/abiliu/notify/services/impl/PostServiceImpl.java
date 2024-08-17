package com.abiliu.notify.services.impl;

import com.abiliu.notify.repositories.FeedRepository;
import com.abiliu.notify.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final Executor taskExecutor;
    private final FeedRepository feedRepository;

    public void startScraping(int numPosts, Duration duration) throws InterruptedException {
        while (true) {
            // List<Feed> feeds = feedRepository.fetchNextFeeds(numPosts);
            System.out.println("Starting next batch of tasks");
            List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            CountDownLatch latch = new CountDownLatch(nums.size());

            for (int num : nums) {
                taskExecutor.execute(() -> {
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                        System.out.println("Thread " + num + "Executed");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        latch.countDown();
                    }
                });
            }
            System.out.println("Waiting for threads to complete");
            latch.await();
            System.out.println("All threads completed!");
            Thread.sleep(duration);
        }
    }
}
