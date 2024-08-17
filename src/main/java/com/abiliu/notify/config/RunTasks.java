package com.abiliu.notify.config;

import com.abiliu.notify.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RunTasks implements CommandLineRunner {
    private final PostService postService;

    @Override
    public void run(String... args) throws Exception {
        Thread thread = new Thread(() -> {
            try {
                postService.startScraping(10, Duration.ofSeconds(10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
    }
}
