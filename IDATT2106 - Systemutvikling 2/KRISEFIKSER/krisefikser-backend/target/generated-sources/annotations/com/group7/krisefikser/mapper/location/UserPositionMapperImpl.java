package com.group7.krisefikser.mapper.location;

import com.group7.krisefikser.dto.request.location.SharePositionRequest;
import com.group7.krisefikser.dto.response.location.GroupMemberPositionResponse;
import com.group7.krisefikser.dto.response.location.HouseholdMemberPositionResponse;
import com.group7.krisefikser.model.location.UserPosition;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T11:35:26+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class UserPositionMapperImpl implements UserPositionMapper {

    @Override
    public UserPosition sharePositionRequestToUserPosition(SharePositionRequest request) {
        if ( request == null ) {
            return null;
        }

        UserPosition userPosition = new UserPosition();

        userPosition.setLatitude( request.getLatitude() );
        userPosition.setLongitude( request.getLongitude() );

        return userPosition;
    }

    @Override
    public HouseholdMemberPositionResponse userPositionToHouseholdMemberPositionResponse(UserPosition userPosition) {
        if ( userPosition == null ) {
            return null;
        }

        HouseholdMemberPositionResponse householdMemberPositionResponse = new HouseholdMemberPositionResponse();

        householdMemberPositionResponse.setLatitude( userPosition.getLatitude() );
        householdMemberPositionResponse.setLongitude( userPosition.getLongitude() );
        householdMemberPositionResponse.setName( userPosition.getName() );

        return householdMemberPositionResponse;
    }

    @Override
    public HouseholdMemberPositionResponse[] userPositionArrayToHouseholdMemberPositionResponseArray(UserPosition[] userPositions) {
        if ( userPositions == null ) {
            return null;
        }

        HouseholdMemberPositionResponse[] householdMemberPositionResponseTmp = new HouseholdMemberPositionResponse[userPositions.length];
        int i = 0;
        for ( UserPosition userPosition : userPositions ) {
            householdMemberPositionResponseTmp[i] = userPositionToHouseholdMemberPositionResponse( userPosition );
            i++;
        }

        return householdMemberPositionResponseTmp;
    }

    @Override
    public GroupMemberPositionResponse userPositionToGroupMemberPositionResponse(UserPosition userPosition) {
        if ( userPosition == null ) {
            return null;
        }

        GroupMemberPositionResponse groupMemberPositionResponse = new GroupMemberPositionResponse();

        groupMemberPositionResponse.setLatitude( userPosition.getLatitude() );
        groupMemberPositionResponse.setLongitude( userPosition.getLongitude() );
        groupMemberPositionResponse.setName( userPosition.getName() );

        return groupMemberPositionResponse;
    }

    @Override
    public GroupMemberPositionResponse[] userPositionArrayToGroupMemberPositionResponseArray(UserPosition[] userPositions) {
        if ( userPositions == null ) {
            return null;
        }

        GroupMemberPositionResponse[] groupMemberPositionResponseTmp = new GroupMemberPositionResponse[userPositions.length];
        int i = 0;
        for ( UserPosition userPosition : userPositions ) {
            groupMemberPositionResponseTmp[i] = userPositionToGroupMemberPositionResponse( userPosition );
            i++;
        }

        return groupMemberPositionResponseTmp;
    }
}
