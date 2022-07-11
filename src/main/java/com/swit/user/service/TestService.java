package com.swit.user.service;

import com.swit.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final UserRepository userRepository;

    public TestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
