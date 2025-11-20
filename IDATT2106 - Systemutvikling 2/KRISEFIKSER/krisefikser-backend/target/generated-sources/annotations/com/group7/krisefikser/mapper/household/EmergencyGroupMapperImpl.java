package com.group7.krisefikser.mapper.household;

import com.group7.krisefikser.dto.request.household.EmergencyGroupRequest;
import com.group7.krisefikser.dto.response.household.EmergencyGroupResponse;
import com.group7.krisefikser.model.household.EmergencyGroup;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T11:35:26+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class EmergencyGroupMapperImpl implements EmergencyGroupMapper {

    private final DatatypeFactory datatypeFactory;

    public EmergencyGroupMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public EmergencyGroupResponse emergencyGroupToResponse(EmergencyGroup emergencyGroup) {
        if ( emergencyGroup == null ) {
            return null;
        }

        EmergencyGroupResponse emergencyGroupResponse = new EmergencyGroupResponse();

        emergencyGroupResponse.setId( emergencyGroup.getId() );
        emergencyGroupResponse.setName( emergencyGroup.getName() );
        emergencyGroupResponse.setCreatedAt( xmlGregorianCalendarToString( dateToXmlGregorianCalendar( emergencyGroup.getCreatedAt() ), "yyyy-MM-dd" ) );

        return emergencyGroupResponse;
    }

    @Override
    public EmergencyGroup emergencyGroupRequestToEntity(EmergencyGroupRequest emergencyGroupRequest) {
        if ( emergencyGroupRequest == null ) {
            return null;
        }

        EmergencyGroup emergencyGroup = new EmergencyGroup();

        emergencyGroup.setName( emergencyGroupRequest.getName() );

        return emergencyGroup;
    }

    private String xmlGregorianCalendarToString( XMLGregorianCalendar xcal, String dateFormat ) {
        if ( xcal == null ) {
            return null;
        }

        if (dateFormat == null ) {
            return xcal.toString();
        }
        else {
            Date d = xcal.toGregorianCalendar().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat( dateFormat );
            return sdf.format( d );
        }
    }

    private XMLGregorianCalendar dateToXmlGregorianCalendar( Date date ) {
        if ( date == null ) {
            return null;
        }

        GregorianCalendar c = new GregorianCalendar();
        c.setTime( date );
        return datatypeFactory.newXMLGregorianCalendar( c );
    }
}
