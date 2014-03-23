package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.exception.MyHoardException;
import java.util.List;

public interface ItemService {

    public List<ItemDTO> getList() throws MyHoardException;

    public List<ItemDTO> getListByUser(UserDTO userDTO) throws MyHoardException;

    public ItemDTO get(int id) throws MyHoardException;

    public ItemDTO create(ItemDTO itemDTO) throws MyHoardException;

    public ItemDTO update(ItemDTO itemDTO) throws MyHoardException;

    public void remove(int id) throws MyHoardException;

}
