package com.group7.krisefikser.mapper.article;

import com.group7.krisefikser.dto.request.article.GeneralInfoRequest;
import com.group7.krisefikser.dto.response.article.GeneralInfoResponse;
import com.group7.krisefikser.model.article.GeneralInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-10T11:35:26+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class GeneralInfoMapperImpl implements GeneralInfoMapper {

    @Override
    public GeneralInfo requestToGeneralInfo(GeneralInfoRequest generalInfoRequest) {
        if ( generalInfoRequest == null ) {
            return null;
        }

        GeneralInfo generalInfo = new GeneralInfo();

        generalInfo.setTheme( stringToTheme( generalInfoRequest.getTheme() ) );
        generalInfo.setTitle( generalInfoRequest.getTitle() );
        generalInfo.setContent( generalInfoRequest.getContent() );

        return generalInfo;
    }

    @Override
    public List<GeneralInfoResponse> generalInfoToResponseList(List<GeneralInfo> generalInfoList) {
        if ( generalInfoList == null ) {
            return null;
        }

        List<GeneralInfoResponse> list = new ArrayList<GeneralInfoResponse>( generalInfoList.size() );
        for ( GeneralInfo generalInfo : generalInfoList ) {
            list.add( generalInfoToResponse( generalInfo ) );
        }

        return list;
    }

    @Override
    public GeneralInfoResponse generalInfoToResponse(GeneralInfo generalInfo) {
        if ( generalInfo == null ) {
            return null;
        }

        GeneralInfoResponse generalInfoResponse = new GeneralInfoResponse();

        if ( generalInfo.getId() != null ) {
            generalInfoResponse.setId( String.valueOf( generalInfo.getId() ) );
        }
        if ( generalInfo.getTheme() != null ) {
            generalInfoResponse.setTheme( generalInfo.getTheme().name() );
        }
        generalInfoResponse.setTitle( generalInfo.getTitle() );
        generalInfoResponse.setContent( generalInfo.getContent() );

        return generalInfoResponse;
    }
}
