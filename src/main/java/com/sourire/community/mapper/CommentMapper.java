package com.sourire.community.mapper;

import com.sourire.community.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author sourire
 * @since 2019-09-09
 */
public interface CommentMapper extends BaseMapper<Comment> {

    @Update("update comment set comment_count = comment_count+1 where id = ${id}")
    void increaseCommentCount(@Param(value = "id") Integer id);
}
