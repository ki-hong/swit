package com.swit.user.controller;

import com.swit.user.dto.PatchUserDto;
import com.swit.user.dto.PostTitleDto;
import com.swit.user.dto.UserMapper;
import com.swit.user.repository.TitleRepository;
import com.swit.user.service.UserService;
import com.swit.user.entity.Title;
import com.swit.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class AdminController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final TitleRepository titleRepository;

    @PatchMapping("/user/{userName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> patchUserInfo(@PathVariable String email, @Valid @RequestBody PatchUserDto patchUserDto){
        User changeUserInfo = userMapper.patchDtoToUser(patchUserDto);
        changeUserInfo.setEmail(userService.findUser(email).getEmail());

        User user = userService.updateUser(changeUserInfo);
        return ResponseEntity.ok(userService.findUser(user.getEmail()));
    }

    @PostMapping("/title")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Title> postTitle (@RequestBody PostTitleDto postTitleDto){
        Title title = userMapper.postTitleDtoToTitle(postTitleDto);
        if (titleRepository.findTitleByName(title.getName()).isPresent()) return ResponseEntity.ok(null);
        return ResponseEntity.ok(titleRepository.save(title));
    }
}
