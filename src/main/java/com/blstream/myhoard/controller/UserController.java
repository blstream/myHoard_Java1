package com.blstream.myhoard.controller;

import com.blstream.myhoard.biz.model.UserDTO;
import com.blstream.myhoard.biz.service.UserService;
import static com.blstream.myhoard.constants.Constants.USER;
import com.blstream.myhoard.exception.ForbiddenException;
import com.blstream.myhoard.exception.MyHoardException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO updateUser(HttpServletRequest request, @PathVariable("id") String id, @Valid @RequestBody UserDTO userDTO) throws MyHoardException {
        UserDTO currentUser = (UserDTO) request.getAttribute(USER);
        if (!currentUser.getId().equals(id) || !currentUser.getEmail().toLowerCase().equals(userDTO.getEmail().toLowerCase())) {
            throw new ForbiddenException();
        }
        userDTO.setId(id);
        
        return userService.update(userDTO);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteItem(HttpServletRequest request, @PathVariable("id") String id) throws MyHoardException {
        UserDTO currentUser = (UserDTO) request.getAttribute(USER);
        if (!currentUser.getId().equals(id)) {
            throw new ForbiddenException();
        }
        
        userService.remove(Integer.parseInt(id));
    }
    
}
