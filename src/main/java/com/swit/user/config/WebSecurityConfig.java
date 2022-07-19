package com.swit.user.config;

import com.swit.user.jwt.JwtAccessDeniedHandler;
import com.swit.user.jwt.JwtAuthenticationEntryPoint;
import com.swit.user.jwt.JwtSecurityConfig;
import com.swit.user.jwt.TokenProvider;
import com.swit.user.service.SecurityUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity // 시큐리티 활성화
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //컨트롤러 메서드에 직접적으로 Role을 부여
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 시큐리티 설정 파일로서 역할을 하기 위해 위의 어뎁터를 상속받아야 함
    @Autowired
    private final SecurityUserService userService;
    @Autowired
    private final TokenProvider tokenProvider;
    @Autowired
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    // 필터 등록
//    @Bean
//    public SecurityAuthenticationFilter securityAuthenticationFilter() {
//        return new SecurityAuthenticationFilter();
//    }

    @Override
    public void configure(WebSecurity web){
        // static의 하위폴더는 무조건 접근이 가능해야 함 -> 인증 무시
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors().and() //필터 활성화
                .csrf().disable() //REST API이고 토큰을 사용하므로 csrf는 해제
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 클라이언트에서 요청하는 헤더에 토큰을 받아 보내는 방식 -> 세션 관리 서버측에서 X
                .and()
                .exceptionHandling() // 인증에 실패했을 경우에 대한 설정
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .apply(new JwtSecurityConfig(tokenProvider))
                .and()
                .authorizeRequests() //여기부터 인증 절차에 대한 설정!
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/v1/test/login").permitAll() // 특정 url에 대한 인증 처리
                .antMatchers("/api/v1/test/signup").permitAll() // 이 권한을 가지면 접근 가능!
                .antMatchers("/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin().disable();

//        http
//                .addFilterBefore(securityAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
