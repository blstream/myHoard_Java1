package com.blstream.myhoard.biz.mapper;

import com.blstream.myhoard.biz.model.TokenDTO;
import com.blstream.myhoard.db.model.TokenDS;
import java.sql.Timestamp;
import java.util.Date;

public class TokenMapper {

    public static TokenDS map(TokenDTO tokenDTO) {
        TokenDS tokenDS = new TokenDS();
        tokenDS.setAccessToken(tokenDTO.getAccessToken());
        tokenDS.setExpiresIn(tokenDTO.getExpiresIn());
        tokenDS.setRefreshToken(tokenDTO.getRefreshToken());
        tokenDS.setCreatedDate(new Timestamp(new Date().getTime()));

        return tokenDS;
    }

    public static TokenDTO map(TokenDS tokenDS) {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setId(tokenDS.getId());
        tokenDTO.setAccessToken(tokenDS.getAccessToken());
        tokenDTO.setUser(UserMapper.map(tokenDS.getUser()));
        tokenDTO.setExpiresIn(tokenDS.getExpiresIn());
        tokenDTO.setRefreshToken(tokenDS.getRefreshToken());
        tokenDTO.setCreatedDate(tokenDS.getCreatedDate());
        tokenDTO.setRefreshToken(tokenDS.getRefreshToken());

        return tokenDTO;
    }

}
