package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.exception.MyHoardException;
import java.util.List;

public interface UserService extends ResourceService<UserDTO> {

    public UserDTO getUserByEmail(String email) throws MyHoardException;

    @Override
    List<UserDTO> getList() throws MyHoardException;

    @Override
    public UserDTO get(int i) throws MyHoardException;

    @Override
    public UserDTO create(UserDTO userDTO) throws MyHoardException;

    @Override
    public UserDTO update(UserDTO t) throws MyHoardException;

    @Override
    public void remove(int i) throws MyHoardException;

}
