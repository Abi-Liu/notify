package com.abiliu.notify.controllers;

import com.abiliu.notify.models.FeedRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feeds")
public class FeedController {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void submitFeed(@RequestBody FeedRequestModel feedRequestModel) {

    }
}
