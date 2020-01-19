package com.sourire.community.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sourire
 * @since 2019-08-28
 */
@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
            //获取浏览数
            questionDTO.setViewCount(getViewCount(question.getId()));
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
        Integer offset = ((pagination.getCurrent() - 1) == -1 ? 0 : (pagination.getCurrent() - 1)) * size;
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
        questionDTO.setViewCount(getViewCount(id));
        return questionDTO;
    }

    @Override
    public void incViewCount(Integer id) {
        Question question = questionMapper.selectById(id);
        //使用redis缓存  将浏览数存在缓存中  每次浏览就加1 定时写入到数据库中
        String key = "viewCount:question:"+id;
        ValueOperations<String, String> redisString = stringRedisTemplate.opsForValue();
        //判断是否有这个缓存key存在
        if(StrUtil.isBlank(redisString.get(key))) {
            redisString.set(key,String.valueOf(question.getViewCount()));
        }
        log.debug("【redis缓存】:key:{}",key);
        redisString.increment(key, 1);
    }

    @Override
    public void updateViewCount(Integer id,Long num) {
        questionMapper.updateViewCount(id,num);
    }

    @Override
    public Long getViewCount(Integer id) {
        String key = "viewCount:question:"+id;
        ValueOperations<String, String> redisString = stringRedisTemplate.opsForValue();
        //判断是否有这个缓存key存在
        String count = redisString.get(key);
        if(!StrUtil.isBlank(count)) {
            return Long.valueOf(count);
        }else{
           return questionMapper.selectById(id).getViewCount();
        }
    }

    /**
     * 根据标签匹配相关问题
     * @param queryDTO
     * @return
     */
    @Override
    public List<QuestionDTO> listRelatedByTags(QuestionDTO queryDTO) {
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        String regexpTags = Arrays.stream(tags).collect(Collectors.joining("|"));
        List<Question> questions = questionMapper.listRelatedByTags(queryDTO.getId(), regexpTags);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
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
