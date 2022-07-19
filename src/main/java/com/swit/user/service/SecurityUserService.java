package com.swit.user.service;

import com.swit.user.repository.UserRepository;
import com.swit.user.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityUserService implements UserDetailsService {
    private final UserRepository userRepository;

    // 시큐리티에서 필수로 구현해야 하는 메서드 구현
    // id 만으로 인증하는 것처럼 보임 -> 사실 시큐리티 내부적으로 id에 해당하는 비밀번호를 인코더로 처리할 수 있도록 하는 서비스가 존재!
    @Override
    public SecurityUser loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException((email)));
    }

    public SecurityUser save(SecurityUser user){
        // 나중에 여기 수정

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
