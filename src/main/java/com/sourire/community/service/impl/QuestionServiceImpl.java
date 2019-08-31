package com.sourire.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sourire.community.dto.QuestionDTO;
import com.sourire.community.entity.Question;
import com.sourire.community.entity.User;
import com.sourire.community.mapper.QuestionMapper;
import com.sourire.community.mapper.UserMapper;
import com.sourire.community.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public List<QuestionDTO> getList(){
        List<Question> list = questionMapper.selectList(null);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : list) {
            Long userId = question.getCreator();
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}
