package com.swit.user.service;

import com.swit.user.entity.User;
import com.swit.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Join {
    private final UserRepository userRepository;

    public Join(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 일반 회원 가입
    public User joinMember(User user){
        verifyExistsEmail(user.getEmail());
        return userRepository.save(user);
    }

    // 카카오 회원 가입


    // 중복 여부 확인
    private void verifyExistsEmail(String email){
        Optional<User> findMembers = userRepository.findByEmail(email);

        if (findMembers.isPresent()){
            throw new RuntimeException();
        }

    }
}
