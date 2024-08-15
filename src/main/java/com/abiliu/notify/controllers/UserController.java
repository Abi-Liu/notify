package com.abiliu.notify.controllers;

import com.abiliu.notify.models.UserRequestModel;
import com.abiliu.notify.models.UserResponseModel;
import com.abiliu.notify.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponseModel findUserbyId(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @GetMapping
    public List<UserResponseModel> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    public UserResponseModel createUser(@RequestBody UserRequestModel userRequestModel) {
        return userService.createUser(userRequestModel);
    }
}
