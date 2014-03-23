package com.blstream.myhoard.db.dao;

import com.blstream.myhoard.db.model.TokenDS;

public interface TokenDAO {

    public TokenDS getByAccessToken(String accessToken);

    public TokenDS getByRefreshToken(String refreshToken);

    public TokenDS getById(int id);

    public void create(TokenDS object);

    public void update(TokenDS object);

}
