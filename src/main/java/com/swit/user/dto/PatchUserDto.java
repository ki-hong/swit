package com.swit.user.dto;

import com.swit.user.entity.Title;
import com.swit.user.entity.User;
import lombok.Getter;

@Getter
public class PatchUserDto {
    private String name;
    private User.UserStatus userStatus;
    private Title title;
}
