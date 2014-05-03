package com.blstream.myhoard.authorization;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.blstream.myhoard.biz.model.UserDTO;

public class UserAuthentication implements Authentication {

    private UserDTO userDTO;

    public UserAuthentication() {
    }

    public UserAuthentication(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public Object getPrincipal() {
        return userDTO;
    }

    @Override
    public String getName() {
        return userDTO.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getCredentials() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAuthenticated() {
        return userDTO != null;
    }

    @Override
    public void setAuthenticated(boolean bln) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
