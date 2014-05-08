package com.blstream.myhoard.biz.service;

import java.util.List;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.exception.MyHoardException;

public interface UserService extends ResourceService<UserDTO> {

    public UserDTO getUserByEmail(String email) throws MyHoardException;

    public List<CollectionDTO> getListOfUserCollections(int id) throws MyHoardException;
    
    @Override
    public List<UserDTO> getList() throws MyHoardException;

    @Override
    public UserDTO get(int i) throws MyHoardException;

    @Override
    public UserDTO create(UserDTO userDTO) throws MyHoardException;

    @Override
    public UserDTO update(UserDTO t) throws MyHoardException;

    @Override
    public void remove(int i) throws MyHoardException;

}
