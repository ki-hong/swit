package com.swit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private String email;
    private String name;
    private String bootcamp;
    private boolean follow; //로그인한 유저가 아닌 경우 팔로우 했는지 여부
    private boolean loginUser; //로그인한 유저인지 여부

    private int countOfFollow;
    private int countOfFollower;
}
