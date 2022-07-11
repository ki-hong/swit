package com.swit.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// 유효성 검사 없음
@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password; //따로 테이블을 빼야 할지?

    private String email;

    private String name;

    private String phone;

    private String address;

    private String bootcamp;

    @OneToOne(mappedBy = "user")
    private UserAuthority userAuthority; // 고려 사항 : 유저 권한은 1:다 vs 1:1

}
