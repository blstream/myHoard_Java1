package com.blstream.myhoard.authorization;

import com.blstream.myhoard.biz.model.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityServiceImpl implements SecurityService {

    @Override
    public UserDTO getCurrentUser() {

        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
