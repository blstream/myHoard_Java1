package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.exception.MyHoardException;
import java.util.List;

public interface CollectionService {

    List<CollectionDTO> getList() throws MyHoardException;

    CollectionDTO get(int i) throws MyHoardException;

    CollectionDTO create(CollectionDTO collectionDTO) throws MyHoardException;

    CollectionDTO update(CollectionDTO collectionDTO) throws MyHoardException;

    void remove(int i) throws MyHoardException;

    List<CollectionDTO> getList(List<String> sortBy, String sortDirection)
            throws MyHoardException;
}
