package com.sourire.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sourire.community.dto.CommentDTO;
import com.sourire.community.entity.Comment;
import com.sourire.community.entity.Question;
import com.sourire.community.entity.User;
import com.sourire.community.enums.CommentTypeEnum;
import com.sourire.community.exception.AppException;
import com.sourire.community.exception.AppExceptionCode;
import com.sourire.community.mapper.CommentMapper;
import com.sourire.community.mapper.QuestionMapper;
import com.sourire.community.mapper.UserMapper;
import com.sourire.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional
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
        if(CommentTypeEnum.QUESTION.getType().equals(type)){
            Question question = questionMapper.selectById(parentId);
            if(question==null){
                throw new AppException(AppExceptionCode.QUESTION_NOT_FOUND);
            }
            isInsert = commentMapper.insert(entity);
            //当回复的是问题，则评论数+1
            questionMapper.increaseCommentCount(entity.getParentId());
        }else{
            Comment comment = commentMapper.selectById(parentId);
            if(comment == null){
                throw new AppException(AppExceptionCode.COMMENT_NOT_FOUND);
            }
            isInsert = commentMapper.insert(entity);
            //回复的是评论，则评论数+1
            commentMapper.increaseCommentCount(entity.getParentId());
        }

        return isInsert>0;
    }

    @Override
    public List<CommentDTO> listByTargetId(Integer id, CommentTypeEnum typeEnum) {
        List<CommentDTO> comments = new ArrayList<>();
        //根据问题id作为父id，查询所有的评论
        List<Comment> commentList = commentMapper.selectList(new QueryWrapper<Comment>()
                .eq("parentId", id).eq("type", typeEnum.getType())
                .orderByDesc("gmt_create"));
        //根据每个评论的用户id，查询对应的用户，并赋值给对应的commentDTO
        if(commentList != null && commentList.size()>0){
            for (Comment comment : commentList) {
                Integer userId = comment.getCommentator();
                User user = userMapper.selectById(userId);
                CommentDTO commentDTO = new CommentDTO();
                BeanUtils.copyProperties(comment,commentDTO);
                commentDTO.setUser(user);
                comments.add(commentDTO);
            }
        }
        return comments;
    }
}
