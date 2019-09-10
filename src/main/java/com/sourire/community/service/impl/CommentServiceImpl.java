package com.sourire.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sourire.community.dto.ResultDTO;
import com.sourire.community.entity.Comment;
import com.sourire.community.entity.Question;
import com.sourire.community.enums.CommentTypeEnum;
import com.sourire.community.exception.AppException;
import com.sourire.community.exception.AppExceptionCode;
import com.sourire.community.mapper.CommentMapper;
import com.sourire.community.mapper.QuestionMapper;
import com.sourire.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author sourire
 * @since 2019-09-09
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean save(Comment entity) {
        int isInsert = 0;
        Integer parentId = entity.getParentId();
        if(parentId == null || parentId == 0){
            throw new AppException(AppExceptionCode.TARGET_PARAM_NOT_FOUND);
        }
        Integer type = entity.getType();
        if(type == null || !CommentTypeEnum.isExist(type)){
            throw new AppException(AppExceptionCode.COMMENT_NOT_FOUND);
        }
        if(CommentTypeEnum.QUESTION.equals(type)){
            Question question = questionMapper.selectById(parentId);
            if(question==null){
                throw new AppException(AppExceptionCode.QUESTION_NOT_FOUND);
            }
            isInsert = commentMapper.insert(entity);
        }else{
            Comment comment = commentMapper.selectById(parentId);
            if(comment == null){
                throw new AppException(AppExceptionCode.COMMENT_NOT_FOUND);
            }
            isInsert = commentMapper.insert(entity);
        }
        questionMapper.increaseCommentCount(entity.getId());

        return isInsert>0;
    }
}
