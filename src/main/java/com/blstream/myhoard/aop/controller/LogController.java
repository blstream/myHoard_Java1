package com.blstream.myhoard.aop.controller;

import com.blstream.myhoard.aop.CountingCalledMethodsAspect;
import com.blstream.myhoard.exception.MyHoardException;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO RT remove
@Controller
@RequestMapping("/log")
public class LogController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HashMap<String, Integer> getLog() throws MyHoardException {

        return CountingCalledMethodsAspect.getCalledMethodsMap();
    }

}
