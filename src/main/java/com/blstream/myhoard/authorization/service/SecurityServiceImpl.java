package com.blstream.myhoard.authorization.service;

import com.blstream.myhoard.biz.model.UserDTO;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public UserDTO getCurrentUser() {

        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    // TODO RT
    @Override
    public boolean currentUserIsOwner(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // TODO RT
    @Override
    public boolean currentUserHasAccess(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
