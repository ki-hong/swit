package com.swit.user.security;

import com.swit.user.entity.User;
import com.swit.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SecurityUserService implements UserDetailsService {
    private final UserRepository userRepository;

    // 시큐리티에서 필수로 구현해야 하는 메서드
    // id 만으로 인증하는 것처럼 보임 -> 사실 시큐리티 내부적으로 id에 해당하는 비밀번호를 인코더로 처리할 수 있도록 하는 서비스가 존재!
    @Override
    public SecurityUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        if (user != null){
            return new SecurityUser(user);
        }
        return null;
    }

    // 현재 시큐리티 컨텍스트에 저장된 username의 정보만 가져옴
    public Optional<User> getMyUserWithAuthorities(){
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findByEmail);
    }
}
