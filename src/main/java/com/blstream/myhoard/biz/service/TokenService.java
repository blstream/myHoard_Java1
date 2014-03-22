package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.mapper.TokenMapper;
import com.blstream.myhoard.biz.model.TokenDTO;
import com.blstream.myhoard.constants.Constants;
import com.blstream.myhoard.db.dao.TokenDAO;
import com.blstream.myhoard.db.dao.UserDAO;
import com.blstream.myhoard.db.model.TokenDS;
import com.blstream.myhoard.db.model.UserDS;
import com.blstream.myhoard.exception.AuthorizationException;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.NotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO RT
@Service("tokenService")
public class TokenService {

    private static final Logger logger = Logger.getLogger(TokenService.class.getCanonicalName());

    @Autowired
    UserService userService;

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private UserDAO userDAO;

    public TokenDTO getByAccessToken(String accessToken) throws MyHoardException {
        TokenDS tokenDS = tokenDAO.getByAccessToken(accessToken);
        if (tokenDS == null) {
            logger.error("getByAccessToken TokenDS null");
            throw new AuthorizationException(Constants.ERROR_CODE_AUTH_TOKEN_INVALID); //TODO Errors constants
        }

        TokenDTO tokenDTO = TokenMapper.map(tokenDS);

        long tokenAge = (new Date().getTime() - tokenDTO.getCreatedDate().getTime()) / 1000;

        if (tokenAge > Constants.TOKEN_KEEP_ALIVE_TIME) {
            logger.error(String.format("Token %s expired", tokenDTO.getAccessToken()));
            throw new AuthorizationException(Constants.ERROR_CODE_AUTH_TOKEN_INVALID);
        }

        return tokenDTO;
    }

    public TokenDTO getByRefreshToken(String refreshToken) throws MyHoardException {
        TokenDS tokenDS = tokenDAO.getByRefreshToken(refreshToken);
        if (tokenDS == null) {
            logger.error("getByRefreshToken TokenDS is null");
            throw new AuthorizationException(Constants.ERROR_CODE_AUTH_TOKEN_INVALID);
        }

        TokenDTO tokenDTO = TokenMapper.map(tokenDS);

        long tokenAge = (new Date().getTime() - tokenDTO.getCreatedDate().getTime()) / 1000;
        if (tokenAge > Constants.TOKEN_KEEP_ALIVE_TIME) {
            logger.error(String.format("Refresh token %s expired", tokenDTO.getRefreshToken()));
            throw new AuthorizationException(Constants.ERROR_CODE_AUTH_TOKEN_INVALID);
        }

        return tokenDTO;
    }

    public TokenDTO create(TokenDTO tokenDTO) throws MyHoardException {
        TokenDS tokenDS = TokenMapper.map(tokenDTO);
        UserDS userDS = userDAO.getByEmail(tokenDTO.getEmail());
        if (userDS == null) {
            throw new NotFoundException("User not found");
        }
        tokenDS.setUser(userDS);

        tokenDAO.create(tokenDS);
        return tokenDTO;
    }

    public TokenDTO update(TokenDTO tokenDTO) throws MyHoardException {
        TokenDS tokenDS = tokenDAO.getById(tokenDTO.getId());
        tokenDS.setAccessToken(tokenDTO.getAccessToken());
        tokenDS.setExpiresIn(tokenDTO.getExpiresIn());
        tokenDS.setRefreshToken(tokenDTO.getRefreshToken());
        tokenDS.setCreatedDate(new Timestamp(new Date().getTime()));
        UserDS userDS = userDAO.getByEmail(tokenDTO.getUser().getEmail());
        if (userDS == null) {
            throw new NotFoundException("User not found");
        }
        tokenDS.setUser(userDS);

        tokenDAO.update(tokenDS);
        return tokenDTO;
    }

}
