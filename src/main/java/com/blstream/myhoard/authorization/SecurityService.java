package com.blstream.myhoard.authorization;

import com.blstream.myhoard.biz.model.UserDTO;

public interface SecurityService {

    public UserDTO getCurrentUser();
    
}
