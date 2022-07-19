package com.swit.user.dto;

import com.swit.user.security.SecurityUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public SecurityUser postDtoToUser(PostUserDto postUserDto);
}
