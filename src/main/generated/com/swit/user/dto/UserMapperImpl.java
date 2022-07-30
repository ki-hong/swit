package com.swit.user.dto;

import com.swit.user.entity.Title;
import com.swit.user.entity.Title.TitleBuilder;
import com.swit.user.entity.User;
import com.swit.user.entity.User.UserBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-30T11:07:42+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User postDtoToUser(PostUserDto postUserDto) {
        if ( postUserDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.email( postUserDto.getEmail() );
        user.password( postUserDto.getPassword() );
        user.name( postUserDto.getName() );
        user.phone( postUserDto.getPhone() );
        user.bootcamp( postUserDto.getBootcamp() );
        user.auth( postUserDto.getAuth() );

        return user.build();
    }

    @Override
    public User patchDtoToUser(PatchUserDto patchUserDto) {
        if ( patchUserDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.name( patchUserDto.getName() );

        return user.build();
    }

    @Override
    public Title postTitleDtoToTitle(PostTitleDto postTitleDto) {
        if ( postTitleDto == null ) {
            return null;
        }

        TitleBuilder title = Title.builder();

        title.name( postTitleDto.getName() );
        title.description( postTitleDto.getDescription() );

        return title.build();
    }
}
