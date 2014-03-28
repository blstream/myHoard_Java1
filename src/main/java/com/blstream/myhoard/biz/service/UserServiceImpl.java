package com.blstream.myhoard.biz.service;

import com.blstream.myhoard.biz.mapper.UserMapper;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.db.dao.UserDAO;
import com.blstream.myhoard.db.model.UserDS;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.NotFoundException;
import com.blstream.myhoard.exception.ResourceAlreadyExistException;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getCanonicalName());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDTO getUserByEmail(String email) throws MyHoardException {
        UserDS userDS = userDAO.getByEmail(email);
        if (userDS == null) {
            throw new NotFoundException("User not found");
        }
        return UserMapper.map(userDS);
    }

    @Override
    public UserDTO get(int id) throws MyHoardException {
        UserDS userDS = userDAO.get(id);
        if (userDS == null) {
            throw new NotFoundException(String.format("User with id = %s not exist", id));
        }
        UserDTO userDTO = UserMapper.map(userDS);
        return userDTO;
    }

    @Override
    public UserDTO create(UserDTO userDTO) throws MyHoardException {
        if (userDAO.getByEmail(userDTO.getEmail()) != null) {
            throw new ResourceAlreadyExistException(String.format("User with email: %s already exist", userDTO.getEmail()));
        }
        // hashing user password
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(hashedPassword);

        userDAO.create(UserMapper.map(userDTO));

        userDTO.setPassword(null); // set password to null because is not should be returned to the controller
        return userDTO;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        UserDS userDS = null;
        try {
            userDS = userDAO.get(Integer.parseInt(userDTO.getId()));
        } catch (NumberFormatException e) {
            logger.info("update", e);
            throw new NotFoundException("User not found, invalid id");
        }
        // hashing user password
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDS.setPassword(hashedPassword);

        userDAO.update(userDS);
        userDTO = UserMapper.map(userDS);
        userDTO.setPassword(null);
        return userDTO;
    }

    @Override
    public void remove(int i) throws MyHoardException {
        userDAO.remove(i);
    }

    // TODO RT
    @Override
    public List<UserDTO> getList() throws MyHoardException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
