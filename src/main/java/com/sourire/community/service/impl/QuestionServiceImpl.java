package com.sourire.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sourire.community.dto.Pagination;
import com.sourire.community.dto.QuestionDTO;
import com.sourire.community.entity.Question;
import com.sourire.community.entity.User;
import com.sourire.community.exception.AppException;
import com.sourire.community.exception.AppExceptionCode;
import com.sourire.community.mapper.QuestionMapper;
import com.sourire.community.mapper.UserMapper;
import com.sourire.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sourire
 * @since 2019-08-28
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<QuestionDTO> selectListByPage(Integer offset ,Integer size){
        List<Question> questions = null;
        //如果查询记录数在1000条之外，则使用高效分页方法
        if (offset > 1000) {
            questions = questionMapper.selectListByPageEffcient(offset, size);
        } else {
            questions = questionMapper.selectListByPage(offset, size);
        }
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            Long userId = question.getCreator();
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }

    @Override
    public Pagination<QuestionDTO> questionPage(Integer currentPage,Integer size) {
        //查出记录总数
        Integer total = questionMapper.selectCount(null);
        //创建分页对象
        Pagination<QuestionDTO> pagination = new Pagination<>(currentPage,size,total);
        //计算偏移量
        Integer offset = (pagination.getCurrent()-1) * size;
        //查询list集合
        List<QuestionDTO> questionDTOS = selectListByPage(offset, size);
        //将集合放入分页对象中
        pagination.setQuestionDTOS(questionDTOS);
        return pagination;
    }

    @Override
    public Pagination<QuestionDTO> questionPageByUserId(long id, Integer current, Integer size) {
        //查出记录总数
        Integer total = questionMapper.selectCount(new QueryWrapper<Question>().eq("creator",id));
        //创建分页对象
        Pagination<QuestionDTO> pagination = new Pagination<>(current,size,total);
        //计算偏移量
        Integer offset = (pagination.getCurrent()-1) * size;
        //查询list集合
        List<QuestionDTO> questionDTOS = selectListPageByUserId(id,offset, size);
        //将集合放入分页对象中
        pagination.setQuestionDTOS(questionDTOS);
        return pagination;
    }

    @Override
    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.selectById(id);
        if (question == null){
            throw new AppException(AppExceptionCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    @Override
    public void incViewCount(Integer id) {
        Question question = questionMapper.selectById(id);
        questionMapper.updateViewCount(id);
    }

    @Override
    public List<QuestionDTO> selectListPageByUserId(long id,Integer offset, Integer size) {
        List<Question> questions = null;
        //如果查询记录数在1000条之外，则使用高效分页方法
        if (offset > 1000) {
            questions = questionMapper.selectListPageByUserIdEffcient(id,offset, size);
        } else {
            questions = questionMapper.selectListPageByUserId(id,offset, size);
        }
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            Long userId = question.getCreator();
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}
