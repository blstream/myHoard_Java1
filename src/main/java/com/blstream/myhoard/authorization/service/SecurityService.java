package com.blstream.myhoard.authorization.service;

import com.blstream.myhoard.biz.model.UserDTO;

public interface SecurityService {

    public UserDTO getCurrentUser();

    // TODO RT
    public boolean currentUserIsOwner(Object object);

    // TODO RT
    public boolean currentUserHasAccess(Object object);

}
