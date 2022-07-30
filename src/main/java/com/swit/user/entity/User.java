package com.swit.user.entity;

import lombok.*;

import javax.persistence.*;

/* 회원 정보
    - code(PK)
    - email : ID, 중복 불가, 인증 필요
    - password
    - name
    - phone
    - bootcamp : 소속 부트캠프 또는 국비 교육
    - auth : USER(일반회원), USER_COMP(소속인증회원), ADMIN(관리자)
    - status : 활동중, 휴면, 탈퇴
 */

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "bootcamp")
    private String bootcamp;

    @Column(name = "auth")
    private String auth;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserStatus status = UserStatus.MEMBER_ACTIVE;

    @OneToOne
    @JoinColumn(name = "title_id")
    private Title title = null;

    @Builder
    public User(String email, String password, String name, String phone, String bootcamp, String auth) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.bootcamp = bootcamp;
        this.auth = auth;
    }

    public enum UserStatus {
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_STOP("정지 상태"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        UserStatus(String status) {
            this.status = status;
        }
    }
}
