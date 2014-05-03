package com.blstream.myhoard.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.biz.enums.RequestMethodEnum;
import com.blstream.myhoard.biz.model.CollectionDTO;
import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.biz.service.UserService;
import com.blstream.myhoard.biz.validator.UserValidator;
import com.blstream.myhoard.exception.ForbiddenException;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.NotFoundException;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final String USER_NOT_EXIST = "User with id = %s not exist";
    private static final String USER_NOT_EXIST_INVALID_ID = "User with id = %s not exist; Invalid Id";
    private static final String GET_USER = "getUser";

    private static final Logger logger = Logger.getLogger(UserController.class.getCanonicalName());

    @Autowired
    private UserService userService;
    @Autowired
    SecurityService securityService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDTO addUser(@RequestBody UserDTO userDTO) throws MyHoardException {
        userValidator.validate(userDTO, RequestMethodEnum.POST);

        return userService.create(userDTO);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO updateUser(@PathVariable("userId") String id, @RequestBody UserDTO userDTO) throws MyHoardException {
        userDTO.setId(id);
        userValidator.validate(userDTO, RequestMethodEnum.PUT);

        return userService.update(userDTO);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteUser(@PathVariable("userId") String id) throws MyHoardException {
        if (!securityService.getCurrentUser().getId().equals(id)) {
            throw new ForbiddenException();
        }

        userService.remove(Integer.parseInt(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDTO> getUserList() throws MyHoardException {

        return userService.getList();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO getUser(@PathVariable("userId") String id) throws MyHoardException {
        UserDTO userDTO;
        try {
            userDTO = userService.get(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            logger.error(GET_USER, e);
            throw new NotFoundException(String.format(USER_NOT_EXIST_INVALID_ID, id));
        } catch (MyHoardException mhe) {
            logger.error(GET_USER, mhe);
            throw new NotFoundException(String.format(USER_NOT_EXIST, id));
        }
        userDTO.setPassword(null);
        return userDTO;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO updateCurrentUser(@RequestBody UserDTO userDTO) throws MyHoardException {
        userDTO.setId(securityService.getCurrentUser().getId());
        userValidator.validate(userDTO, RequestMethodEnum.PUT);

        return userService.update(userDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteCurrentUser() throws MyHoardException {

        userService.remove(Integer.parseInt(securityService.getCurrentUser().getId()));
    }

    @RequestMapping(value = "/{userId}/collections", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CollectionDTO> getListOfUserCollections(@PathVariable("userId") String id) throws MyHoardException {
        try {
            return userService.getListOfUserCollections(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            logger.error(GET_USER, e);
            throw new NotFoundException(String.format(USER_NOT_EXIST_INVALID_ID, id));
        } catch (MyHoardException mhe) {
            logger.error(GET_USER, mhe);
            throw new NotFoundException(String.format(USER_NOT_EXIST, id));
        }
    }
}
