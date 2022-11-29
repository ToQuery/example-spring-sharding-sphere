package io.github.toquery.example.spring.sharding.sphere.bff.open.controller;

import io.github.toquery.example.spring.sharding.sphere.bff.open.service.OpenUserService;
import io.github.toquery.example.spring.sharding.sphere.bff.open.model.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/open/user")
@RequiredArgsConstructor
public class OpenUserController {

    private final OpenUserService userService;

    @GetMapping("/{userId}")
    public UserInfoResponse userInfo(@PathVariable Long userId) {
        return userService.userInfo(userId);
    }


    @GetMapping("/save")
    public UserInfoResponse save() {
        return userService.save();
    }
}
