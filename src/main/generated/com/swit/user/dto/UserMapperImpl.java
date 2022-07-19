package com.swit.user.dto;

import com.swit.user.security.SecurityUser;
import com.swit.user.security.SecurityUser.SecurityUserBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-19T21:54:43+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public SecurityUser postDtoToUser(PostUserDto postUserDto) {
        if ( postUserDto == null ) {
            return null;
        }

        SecurityUserBuilder securityUser = SecurityUser.builder();

        securityUser.email( postUserDto.getEmail() );
        securityUser.password( postUserDto.getPassword() );
        securityUser.auth( postUserDto.getAuth() );

        return securityUser.build();
    }
}
