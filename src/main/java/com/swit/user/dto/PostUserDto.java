package com.swit.user.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class PostUserDto {
    @Email
    private String email;

    @NotBlank(message = "비밀번호가 입력되지 않았습니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$",
            message = "비밀번호는 영문, 특수문자, 숫자가 모두 하나 이상 포함된 8글자 이상의 문자열이어야 합니다.")
    private String password;

    @NotBlank
    private String auth;

    @NotBlank(message = "이름이 입력되지 않았습니다.")
    private String name;

    @NotBlank(message = "휴대폰 번호가 입력되지 않았습니다.")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String phone;

    private String bootcamp;
}
