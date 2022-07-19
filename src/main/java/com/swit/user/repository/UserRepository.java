package com.swit.user.repository;

import com.swit.user.security.SecurityUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SecurityUser, Long> {
    Optional<SecurityUser> findByEmail(String email);

//    @EntityGraph(attributePaths = "authorities") //Eager 조회로 authority 정보 함께 가져옴 -> 우리는 String이라서 필요없음 변겅시 사용
//    Optional<SecurityUser> findOneWithAuthoritiesByEmail(String email);
}
