package com.swit.user.controller;

import com.swit.user.repository.FollowRepository;
import com.swit.user.service.UserService;
import com.swit.user.entity.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
public class FollowController {
    private final FollowRepository followRepository;
    private final UserService userService;

    @PostMapping("/follow/{toUser}")
    public Follow followUser(@PathVariable String toUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.saveFollow(authentication.getName(), toUser);
    }

    @DeleteMapping("/follow/{toUser}")
    public void unFollowUser(@PathVariable String toUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long id = userService.getFollowIdByFromEmailToId(authentication.getName(), toUser);
        followRepository.deleteById(id);
    }
}
