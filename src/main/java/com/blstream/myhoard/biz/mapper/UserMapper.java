package com.blstream.myhoard.biz.mapper;

import java.util.ArrayList;
import java.util.List;

import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.db.model.UserDS;

public class UserMapper {
    
    public static UserDS map(UserDTO userDTO) {
        UserDS userDS = new UserDS();
        userDS.setUsername(userDTO.getUsername());
        userDS.setEmail(userDTO.getEmail());
        userDS.setPassword(userDTO.getPassword());
        
        return userDS;
    }
    
    public static UserDTO map(UserDS userDS) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(String.valueOf(userDS.getId()));
        userDTO.setUsername(userDS.getUsername());
        userDTO.setEmail(userDS.getEmail());
        userDTO.setPassword(userDS.getPassword());
        
        return userDTO;
    }
    
    public static List<UserDTO> map(List<UserDS> userDSList) {
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        for (UserDS u : userDSList) {
            UserDTO userDTO = map(u);
            userDTO.setPassword(null);
            userDTOList.add(userDTO);
        }
        
        return userDTOList;
    }
    
}
