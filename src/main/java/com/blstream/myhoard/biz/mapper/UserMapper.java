package com.blstream.myhoard.biz.mapper;

import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.db.model.UserDS;

// TODO RT list mapper
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
                userDTO.setUsername(userDS.getUsername());
                userDTO.setEmail(userDS.getEmail());
                userDTO.setPassword(userDS.getPassword());
                userDTO.setId(String.valueOf(userDS.getId()));

                return userDTO;
        }

}
