package com.group7.krisefikser.mapper.household;

import com.group7.krisefikser.dto.request.household.AddNonUserMemberRequest;
import com.group7.krisefikser.dto.request.household.UpdateNonUserMemberRequest;
import com.group7.krisefikser.enums.NonUserMemberType;
import com.group7.krisefikser.model.household.NonUserMember;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T11:35:26+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class NonUserMemberMapperImpl implements NonUserMemberMapper {

    @Override
    public NonUserMember addNonUserMemberRequestToNonUserMember(AddNonUserMemberRequest addNonUserMemberRequest) {
        if ( addNonUserMemberRequest == null ) {
            return null;
        }

        NonUserMember nonUserMember = new NonUserMember();

        nonUserMember.setName( addNonUserMemberRequest.getName() );
        nonUserMember.setType( addNonUserMemberRequest.getType() );

        return nonUserMember;
    }

    @Override
    public NonUserMember updateNonUserMemberRequestToNonUserMember(UpdateNonUserMemberRequest updateNonUserMemberRequest) {
        if ( updateNonUserMemberRequest == null ) {
            return null;
        }

        NonUserMember nonUserMember = new NonUserMember();

        nonUserMember.setName( updateNonUserMemberRequest.getName() );
        if ( updateNonUserMemberRequest.getType() != null ) {
            nonUserMember.setType( Enum.valueOf( NonUserMemberType.class, updateNonUserMemberRequest.getType() ) );
        }
        nonUserMember.setId( updateNonUserMemberRequest.getId() );

        return nonUserMember;
    }
}
