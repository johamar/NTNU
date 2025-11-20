package com.group7.krisefikser.mapper.household;

import com.group7.krisefikser.dto.request.household.HouseholdRequest;
import com.group7.krisefikser.dto.response.household.HouseholdResponse;
import com.group7.krisefikser.model.household.Household;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T11:35:26+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class HouseholdMapperImpl implements HouseholdMapper {

    @Override
    public Household householdRequestToHousehold(HouseholdRequest householdRequest) {
        if ( householdRequest == null ) {
            return null;
        }

        Household household = new Household();

        household.setName( householdRequest.getName() );
        household.setLongitude( householdRequest.getLongitude() );
        household.setLatitude( householdRequest.getLatitude() );

        return household;
    }

    @Override
    public HouseholdResponse householdToHouseholdResponse(Household household) {
        if ( household == null ) {
            return null;
        }

        HouseholdResponse householdResponse = new HouseholdResponse();

        householdResponse.setId( household.getId() );
        householdResponse.setName( household.getName() );
        householdResponse.setLongitude( household.getLongitude() );
        householdResponse.setLatitude( household.getLatitude() );

        return householdResponse;
    }
}
