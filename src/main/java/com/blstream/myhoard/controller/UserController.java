package com.blstream.myhoard.controller;

import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.biz.service.UserService;
import com.blstream.myhoard.exception.MyHoardException;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO RT user crud
@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getCanonicalName());

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDTO addUser(@Valid @RequestBody UserDTO user) throws MyHoardException {

        return userService.create(user);
    }

}
