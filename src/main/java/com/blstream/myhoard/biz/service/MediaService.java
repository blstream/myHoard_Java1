package com.blstream.myhoard.biz.service;

import java.util.List;

import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.exception.MyHoardException;

public interface MediaService extends ResourceService<MediaDTO> {

    @Override
    public List<MediaDTO> getList() throws MyHoardException;

    @Override
    public MediaDTO get(int id) throws MyHoardException;

    @Override
    public MediaDTO create(MediaDTO mediaDTO) throws MyHoardException;

    @Override
    public MediaDTO update(MediaDTO mediaDTO) throws MyHoardException;

    @Override
    public void remove(int id) throws MyHoardException;
}
