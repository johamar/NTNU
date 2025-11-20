package com.group7.krisefikser.mapper.user;

import com.group7.krisefikser.dto.request.user.RegisterRequest;
import com.group7.krisefikser.model.user.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T11:35:26+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User registerRequestToUser(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( registerRequest.getEmail() );
        user.setName( registerRequest.getName() );
        user.setPassword( registerRequest.getPassword() );

        return user;
    }
}
