package com.sourire.community.controller;


import com.sourire.community.dto.CommentDTO;
import com.sourire.community.dto.ResultDTO;
import com.sourire.community.entity.Comment;
import com.sourire.community.entity.User;
import com.sourire.community.exception.AppExceptionCode;
import com.sourire.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author sourire
 * @since 2019-09-09
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public ResultDTO comment(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(AppExceptionCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setCommentator((int) user.getId());
        comment.setGmtCreate(new Date());
        commentService.save(comment);
        return ResultDTO.okOf();
    }

}

