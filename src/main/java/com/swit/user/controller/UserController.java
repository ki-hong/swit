package com.swit.user.controller;

import com.swit.user.dto.*;
import com.swit.user.service.UserService;
import com.swit.user.entity.User;
import com.swit.user.jwt.JwtFilter;
import com.swit.user.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
 * 구현 완료
 - login : 로그인
 - getMyInfo : 현재 로그인한 회원 정보 가져오기 (운영자, 회원 접근 가능)
 - patchMyInfo : 현재 로그인한 회원 정보 수정하기
 - patchUserInfo : 지정한 회원의 정보 수정하기 (운영자만 접근 가능)


 * 미구현 & 검토중
 - signup : 회원가입시 이메일 인증 기능 추가
 - getUsersInfo : 가입된 모든 회원 정보 가져오기 (운영자만 접근 가능)
 - getFollows/Followers : 해당 유저가 팔로우 하는 유저, 해당 유저를 팔로우 하는 유저 조회하기
 - getProfile : 지정한 회원의 프로필 가져오기 -> 해당 유저가 작성한 글을 볼 수 있어야 함 - 누군가 나를 팔로우 하면 알림이 가도록 해야함..
 - findMyId, findMyPassword : 아이디 찾기, 비밀번호 찾기
 */


@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
@RestController
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/signup")
    public User signup(@Valid @RequestBody PostUserDto postUserDto){
        User user = userMapper.postDtoToUser(postUserDto);
        return userService.saveUser(user);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<User> getUserInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        return ResponseEntity.ok(userService.findUser(userDetails.getUsername()));
    }

    @PatchMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<User> patchMyInfo(@Valid @RequestBody PatchUserDto patchUserDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userInfo = (UserDetails) principal;

        User changeUserInfo = userMapper.patchDtoToUser(patchUserDto);
        changeUserInfo.setEmail(userInfo.getUsername());

        User user = userService.updateUser(changeUserInfo);
        return ResponseEntity.ok(userService.findUser(user.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto){
        if (userService.findUser(loginDto.getEmail()).getStatus() != User.UserStatus.MEMBER_ACTIVE){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // 정리하자
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword(), null);

        // authenticationManager 내부에는 userDetailService와 AuthenticationManager, AuthenticationProvider가 생성되서 사용됨
        // authenticationManagerBuilder가 유효성 검증 후 Authentication 객체를 생성
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 토큰 만들기
        String jwt = tokenProvider.createToken(authentication);

        // 응답 헤더에 jwt 추가
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer" + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/profile/{userEmail}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable String userEmail){
        String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        ProfileDto profileDto = userService.getProfile(userEmail, loginEmail);
        return ResponseEntity.ok(profileDto);
    }

    /*
    개발 진행중
     */
//    @GetMapping("/kakaoUser")
//    public String getCode(@RequestParam("code") String code){
//
//    }

}
