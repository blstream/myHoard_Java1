package com.blstream.myhoard.biz.service;

import java.util.List;

import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.exception.MyHoardException;

public interface ItemService extends ResourceService<ItemDTO> {

	public List<ItemDTO> getListByUser() throws MyHoardException;

	public List<ItemDTO> getListByUser(UserDTO userDTO) throws MyHoardException;

	public List<ItemDTO> getList(String name, int collection, String owner)
			throws MyHoardException;

	public List<ItemDTO> getList(int id, List<String> sortBy,
			String sortDirection);

    public boolean isUniqueNameOfCollectionItem(String name, int collectionId);
}
