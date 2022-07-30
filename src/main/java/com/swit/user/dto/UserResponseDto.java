package com.swit.user.dto;

import com.swit.user.entity.User;

public class UserResponseDto {
    private long userId;
    private String email;
    private String name;
    private String phone;
    private String bootcamp;
    private String auth;
    private User.UserStatus userStatus;

    public String getUserStatus(){ return userStatus.getStatus();}
}
