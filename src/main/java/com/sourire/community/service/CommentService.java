package com.sourire.community.service;

import com.sourire.community.dto.CommentDTO;
import com.sourire.community.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sourire.community.enums.CommentTypeEnum;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author sourire
 * @since 2019-09-09
 */
public interface CommentService extends IService<Comment> {

    List<CommentDTO> listByTargetId(Integer id, CommentTypeEnum typeEnum);

}
