package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.model.TokenDTO;
import com.blstream.myhoard.exception.MyHoardException;

public interface TokenService {

    public TokenDTO getByAccessToken(String accessToken) throws MyHoardException;

    public TokenDTO getByRefreshToken(String refreshToken) throws MyHoardException;

    public TokenDTO create(TokenDTO tokenDTO) throws MyHoardException;

    public TokenDTO update(TokenDTO tokenDTO) throws MyHoardException;

}
