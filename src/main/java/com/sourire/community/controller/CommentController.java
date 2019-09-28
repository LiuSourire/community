package com.sourire.community.controller;


import com.sourire.community.dto.CommentDTO;
import com.sourire.community.dto.ResultDTO;
import com.sourire.community.entity.Comment;
import com.sourire.community.entity.User;
import com.sourire.community.enums.CommentTypeEnum;
import com.sourire.community.exception.AppExceptionCode;
import com.sourire.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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
        if(commentDTO == null  || StringUtils.isBlank(commentDTO.getContent())){
            return ResultDTO.errorOf(AppExceptionCode.CONTENT_NOT_EMPTY);
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

    /**
     * 获取二级评论
     */
    @GetMapping("/comment/{id}")
    public ResultDTO comments(@PathVariable Integer id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT_FIRST);
        return ResultDTO.okOf(commentDTOS);
    }
}

