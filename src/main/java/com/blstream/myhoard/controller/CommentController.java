package com.blstream.myhoard.controller;

import com.blstream.myhoard.authorization.service.SecurityService;
import com.blstream.myhoard.biz.enums.RequestMethodEnum;
import com.blstream.myhoard.biz.model.CommentDTO;
import com.blstream.myhoard.biz.service.CommentService;
import com.blstream.myhoard.biz.validator.CommentValidator;
import com.blstream.myhoard.biz.validator.RequestValidator;
import com.blstream.myhoard.exception.ForbiddenException;
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
    @Autowired
    private SecurityService securityService;
    @Autowired
    private RequestValidator requestValidator;
    @Autowired
    private CommentValidator commentValidator;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO) throws MyHoardException {
        commentValidator.validate(commentDTO, RequestMethodEnum.POST);

        return commentService.create(commentDTO);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommentDTO getComment(@PathVariable("commentId") String id) throws MyHoardException {
        requestValidator.validId(id);

        return commentService.get(id);
    }

    // allowed only if current user is author of comment or user is owner of commented collection
    @RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommentDTO updateComment(@PathVariable("commentId") String id, @RequestBody CommentDTO commentDTO) throws MyHoardException {
        requestValidator.validId(id);
        commentValidator.validate(commentDTO, RequestMethodEnum.PUT);

        CommentDTO srcCommentDTO = commentService.get(id);
        commentDTO.setId(id);

        if (!securityService.getCurrentUser().getId().equals(srcCommentDTO.getOwner().getId())
                && !securityService.getCurrentUser().getId().equals(srcCommentDTO.getCollectionDTO().getOwner().getId())) {
            throw new ForbiddenException();
        }

        return commentService.update(commentDTO);
    }

    // allowed only if current user is author of comment or user is owner of commented collection
    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteComment(@PathVariable("commentId") String id) throws MyHoardException {
        requestValidator.validId(id);
        CommentDTO commentDTO = commentService.get(id);

        if (!securityService.getCurrentUser().getId().equals(commentDTO.getOwner().getId())
                && !securityService.getCurrentUser().getId().equals(commentDTO.getCollectionDTO().getOwner().getId())) {
            throw new ForbiddenException();
        }

        commentService.remove(Integer.parseInt(id));
    }

}
