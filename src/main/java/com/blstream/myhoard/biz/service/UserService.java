package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.exception.MyHoardException;

public interface UserService {

    public UserDTO getUserByEmail(String email) throws MyHoardException;

    public UserDTO get(int i) throws MyHoardException;

    public UserDTO create(UserDTO userDTO) throws MyHoardException;

    public UserDTO update(UserDTO t) throws MyHoardException;

    public void remove(int i) throws MyHoardException;

}
