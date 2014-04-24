package com.blstream.myhoard.controller;

import com.blstream.myhoard.biz.model.CommentDTO;
import com.blstream.myhoard.biz.service.CommentService;
import com.blstream.myhoard.exception.MyHoardException;
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

@Controller
@RequestMapping("/comments")
public class CommentController {

    private static final Logger logger = Logger.getLogger(CommentController.class.getCanonicalName());
    
    @Autowired
    private CommentService commentService;

    // TODO RT - implement
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO) throws MyHoardException {
        // TODO RT - validator

        return commentService.create(commentDTO);
    }

    // TODO RT - implement
    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommentDTO getComment(@PathVariable("commentId") String id) {
        CommentDTO commentDTO;
        // TODO RT - get comment
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // TODO RT - implement
    @RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommentDTO updateComment(@PathVariable("commentId") String id, @RequestBody CommentDTO commentDTO) throws MyHoardException {
        // TODO RT - validator
        // TODO RT - update comment
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // TODO RT - implement
    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteComment(@PathVariable("commentId") String id) throws MyHoardException {
        // TODO RT - delete comment
    }
}
