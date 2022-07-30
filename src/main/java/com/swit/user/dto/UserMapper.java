package com.swit.user.dto;

import com.swit.user.entity.Title;
import com.swit.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User postDtoToUser(PostUserDto postUserDto);
    public User patchDtoToUser(PatchUserDto patchUserDto);
    public Title postTitleDtoToTitle(PostTitleDto postTitleDto);
}
