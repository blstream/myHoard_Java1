package com.blstream.myhoard.controller;

import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.biz.service.IResourceService;
import com.blstream.myhoard.exception.MyHoardException;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO RT user crud
// TODO RT validation
@Controller
@RequestMapping("/users")
public class UserController {
        
        private static final String ADD_USER = "addUser";
        
        private static final Logger logger = Logger.getLogger(UserController.class.getCanonicalName());

        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        IResourceService<UserDTO> userService;

        // TODO RT validation
        @RequestMapping(method = RequestMethod.POST)
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public UserDTO addUser(@Valid @RequestBody UserDTO user) throws MyHoardException {

                String hashedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(hashedPassword);
                
                logger.info(String.format("%s hashed pass - %s", ADD_USER, hashedPassword));

                if (user.getUsername() == null) {
                        user.setUsername(user.getEmail());
                        logger.info(ADD_USER + " - setting username");
                }

                return userService.create(user);
        }

}
