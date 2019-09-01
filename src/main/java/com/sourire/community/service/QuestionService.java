package com.sourire.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sourire.community.dto.Pagination;
import com.sourire.community.dto.QuestionDTO;
import com.sourire.community.entity.Question;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sourire
 * @since 2019-08-28
 */
public interface QuestionService extends IService<Question> {
    /**
     * 获取问题列表
     * @return
     */
    public List<QuestionDTO> selectListByPage(Integer offset ,Integer size);

    public Pagination<QuestionDTO> questionPage(Integer currentPage, Integer size);
}
