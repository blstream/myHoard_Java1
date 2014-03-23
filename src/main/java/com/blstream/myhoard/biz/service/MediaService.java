package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.model.MediaDTO;
import com.blstream.myhoard.exception.MyHoardException;
import java.util.List;

public interface MediaService {

    public List<MediaDTO> getList() throws MyHoardException;

    public MediaDTO get(int id) throws MyHoardException;

    public MediaDTO create(MediaDTO mediaDTO) throws MyHoardException;

    public MediaDTO update(MediaDTO mediaDTO) throws MyHoardException;

    public void remove(int id) throws MyHoardException;
}
