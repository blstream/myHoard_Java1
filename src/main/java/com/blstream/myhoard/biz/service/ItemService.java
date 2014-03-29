package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.exception.MyHoardException;
import java.util.List;

public interface ItemService extends ResourceService<ItemDTO> {

    public List<ItemDTO> getListByUser() throws MyHoardException;

    public List<ItemDTO> getListByUser(UserDTO userDTO) throws MyHoardException;

    public List<ItemDTO> getList(String name, int collection, String owner) throws MyHoardException;

    @Override
    public List<ItemDTO> getList() throws MyHoardException;

    @Override
    public ItemDTO get(int id) throws MyHoardException;

    @Override
    public ItemDTO create(ItemDTO itemDTO) throws MyHoardException;

    @Override
    public ItemDTO update(ItemDTO itemDTO) throws MyHoardException;

    @Override
    public void remove(int id) throws MyHoardException;

}
