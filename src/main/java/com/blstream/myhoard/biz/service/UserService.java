package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.mapper.UserMapper;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.db.dao.UserDAO;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.ResourceAlreadyExistException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO RT implement
@Service("userService")
public class UserService extends ResourceService<UserDTO> {
        
        private static final Logger logger = Logger.getLogger(UserService.class.getCanonicalName());
        
        @Autowired
        private UserDAO userDAO;

        // TODO RT implement
        @Override
        public List<UserDTO> getList() throws MyHoardException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        // TODO RT implement
        @Override
        public UserDTO get(int i) throws MyHoardException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public UserDTO create(UserDTO userDTO) throws MyHoardException {
                if(!userDAO.getByEmail(userDTO.getEmail()).isEmpty()){
                        throw new ResourceAlreadyExistException(String.format("User with email: %s already exist", userDTO.getEmail()));
                }
                userDAO.create(UserMapper.map(userDTO));
                
                userDTO.setPassword(null); // set password to null because is not should be returned to the controller
                return userDTO;
        }

        // TODO RT implement
        @Override
        public UserDTO update(UserDTO t) throws MyHoardException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        // TODO RT implement
        @Override
        public void remove(int i) throws MyHoardException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

}
