package com.abiliu.notify.controllers;

import com.abiliu.notify.models.FeedRequestModel;
import com.abiliu.notify.services.FeedService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feeds")
public class FeedController {
    private final FeedService feedService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void submitFeed(HttpServletRequest request, @RequestBody FeedRequestModel feedRequestModel) {
        int userId = (Integer) request.getAttribute("userId");
        feedService.createFeed(feedRequestModel, userId);
    }
}
