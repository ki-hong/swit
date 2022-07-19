package com.swit.user.security;

import com.swit.user.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 매 요청마다 한번씩 확인하기 위한 필터
// 컨트롤러 로직을 수행하기 이전에 필터를 호출한다

// 클라이언트에서 요청이 오면 먼저 필터로 옴
public class SecurityAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityUserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 여기는 실제 구현시 수정해야 함 *** 테스트를 위한 코드
//        UserInfoDto infoDto = new UserInfoDto();
//        infoDto.setEmail("11@naver.com");
//        infoDto.setPassword("2");
//        userService.save(infoDto);
//        System.out.println("테스트를 위해 사용자가 저장되었어요!");

        // 유저 읽어오기 *** 실제 구현시 요청에서 읽어올 것
        UserDetails authentication = userService.loadUserByUsername("user@naver.com");

        // 요청으로부터 id와 비밀번호 얻어오기
        // 아래 필터에 id와 비밀번호를 입력해 권한 인증

        // 아이디 비밀번호를 이용한 인증을 담당하는 필터 -> 토큰(인증 객체)을 만듬
       UsernamePasswordAuthenticationToken auth = new
               UsernamePasswordAuthenticationToken(authentication, "user@naver.com", authentication.getAuthorities());
       SecurityContextHolder.getContext().setAuthentication(auth); //인증 객체를 넣어줌
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        filterChain.doFilter(request, response);
    }
}
