package com.group7.krisefikser.mapper.user;

import com.group7.krisefikser.dto.response.user.AdminResponse;
import com.group7.krisefikser.model.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T11:35:26+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class AdminMapperImpl implements AdminMapper {

    @Override
    public List<AdminResponse> userToAdminResponse(List<User> superAdmins) {
        if ( superAdmins == null ) {
            return null;
        }

        List<AdminResponse> list = new ArrayList<AdminResponse>( superAdmins.size() );
        for ( User user : superAdmins ) {
            list.add( userToAdminResponse1( user ) );
        }

        return list;
    }

    protected AdminResponse userToAdminResponse1(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String email = null;

        id = user.getId();
        email = user.getEmail();

        AdminResponse adminResponse = new AdminResponse( id, email );

        return adminResponse;
    }
}
