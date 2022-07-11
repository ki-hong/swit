package com.swit.user.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated
    private AuthorCode authorCode = AuthorCode.COMMON;

    @OneToOne(mappedBy = "authority")
    private UserAuthority userAuthority;

    private enum AuthorCode {
        COMMON("일반 회원"),
        ADMIN("관리자"),
        STUDENT("인증 회원");

        @Getter
        private String status;

        AuthorCode(String status) {
            this.status = status;
        }
    }
}
