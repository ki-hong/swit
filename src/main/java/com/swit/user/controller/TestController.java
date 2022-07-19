package com.swit.user.controller;

import com.swit.user.dto.LoginDto;
import com.swit.user.dto.PostUserDto;
import com.swit.user.dto.TokenDto;
import com.swit.user.dto.UserMapper;
import com.swit.user.jwt.JwtFilter;
import com.swit.user.jwt.TokenProvider;
import com.swit.user.security.SecurityUser;
import com.swit.user.service.SecurityUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
@RestController
public class TestController {
    private final SecurityUserService userService;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;

    // 메모리에 권한을 만들어주는 권한 매니저
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/signup")
    public SecurityUser signup(@RequestBody PostUserDto postUserDto){
        SecurityUser user = userMapper.postDtoToUser(postUserDto);
        return userService.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto){
        SecurityUser user = userService.loadUserByUsername(loginDto.getEmail());
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword(), user.getAuthorities());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 토큰 만들기
       String jwt = tokenProvider.createToken(authentication);

        // 응답 헤더에 jwt 추가
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer" + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
