package com.group7.krisefikser.mapper.household;

import com.group7.krisefikser.dto.request.household.JoinHouseholdRequest;
import com.group7.krisefikser.dto.response.household.JoinHouseholdRequestResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T11:35:26+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class JoinRequestMapperImpl implements JoinRequestMapper {

    @Override
    public JoinHouseholdRequestResponse joinRequestToResponse(JoinHouseholdRequest joinRequest) {
        if ( joinRequest == null ) {
            return null;
        }

        JoinHouseholdRequestResponse joinHouseholdRequestResponse = new JoinHouseholdRequestResponse();

        joinHouseholdRequestResponse.setId( joinRequest.getId() );
        joinHouseholdRequestResponse.setUserId( joinRequest.getUserId() );
        joinHouseholdRequestResponse.setHouseholdId( joinRequest.getHouseholdId() );

        return joinHouseholdRequestResponse;
    }

    @Override
    public List<JoinHouseholdRequestResponse> joinRequestListToResponseList(List<JoinHouseholdRequest> joinRequests) {
        if ( joinRequests == null ) {
            return null;
        }

        List<JoinHouseholdRequestResponse> list = new ArrayList<JoinHouseholdRequestResponse>( joinRequests.size() );
        for ( JoinHouseholdRequest joinHouseholdRequest : joinRequests ) {
            list.add( joinRequestToResponse( joinHouseholdRequest ) );
        }

        return list;
    }
}
