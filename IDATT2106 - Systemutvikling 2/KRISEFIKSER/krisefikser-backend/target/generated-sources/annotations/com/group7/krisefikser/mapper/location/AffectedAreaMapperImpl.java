package com.group7.krisefikser.mapper.location;

import com.group7.krisefikser.dto.request.location.AffectedAreaRequest;
import com.group7.krisefikser.dto.response.location.AffectedAreaResponse;
import com.group7.krisefikser.model.location.AffectedArea;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T11:35:26+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class AffectedAreaMapperImpl implements AffectedAreaMapper {

    @Override
    public AffectedAreaResponse affectedAreaToResponse(AffectedArea affectedArea) {
        if ( affectedArea == null ) {
            return null;
        }

        AffectedAreaResponse affectedAreaResponse = new AffectedAreaResponse();

        affectedAreaResponse.setId( affectedArea.getId() );
        affectedAreaResponse.setName( affectedArea.getName() );
        affectedAreaResponse.setLongitude( affectedArea.getLongitude() );
        affectedAreaResponse.setLatitude( affectedArea.getLatitude() );
        affectedAreaResponse.setHighDangerRadiusKm( affectedArea.getHighDangerRadiusKm() );
        affectedAreaResponse.setMediumDangerRadiusKm( affectedArea.getMediumDangerRadiusKm() );
        affectedAreaResponse.setLowDangerRadiusKm( affectedArea.getLowDangerRadiusKm() );
        affectedAreaResponse.setSeverityLevel( affectedArea.getSeverityLevel() );
        affectedAreaResponse.setDescription( affectedArea.getDescription() );
        if ( affectedArea.getStartDate() != null ) {
            affectedAreaResponse.setStartDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( affectedArea.getStartDate() ) );
        }

        return affectedAreaResponse;
    }

    @Override
    public AffectedArea requestToAffectedArea(AffectedAreaRequest affectedAreaRequest) {
        if ( affectedAreaRequest == null ) {
            return null;
        }

        AffectedArea affectedArea = new AffectedArea();

        affectedArea.setName( affectedAreaRequest.getName() );
        affectedArea.setLongitude( affectedAreaRequest.getLongitude() );
        affectedArea.setLatitude( affectedAreaRequest.getLatitude() );
        affectedArea.setHighDangerRadiusKm( affectedAreaRequest.getHighDangerRadiusKm() );
        affectedArea.setMediumDangerRadiusKm( affectedAreaRequest.getMediumDangerRadiusKm() );
        affectedArea.setLowDangerRadiusKm( affectedAreaRequest.getLowDangerRadiusKm() );
        if ( affectedAreaRequest.getSeverityLevel() != null ) {
            affectedArea.setSeverityLevel( affectedAreaRequest.getSeverityLevel() );
        }
        affectedArea.setDescription( affectedAreaRequest.getDescription() );
        affectedArea.setStartDate( stringToDateTime( affectedAreaRequest.getStartDate() ) );

        return affectedArea;
    }
}
