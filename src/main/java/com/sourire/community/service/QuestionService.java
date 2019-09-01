package com.sourire.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sourire.community.dto.Pagination;
import com.sourire.community.dto.QuestionDTO;
import com.sourire.community.entity.Question;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sourire
 * @since 2019-08-28
 */
public interface QuestionService extends IService<Question> {
    /**
     * 获取问题列表
     * @param offset 偏移量
     * @param size   每页条数
     * @return
     */
    public List<QuestionDTO> selectListByPage(Integer offset, Integer size);

    /**
     * 根据用户id获取问题列表
     * @param offset 偏移量
     * @param size   每页条数
     * @return
     */
    public List<QuestionDTO> selectListPageByUserId(long id, Integer offset, Integer size);

    /**
     * 分页获取问题列表
     * @param currentPage 当前页
     * @param size        每页条数
     * @return
     */
    public Pagination<QuestionDTO> questionPage(Integer currentPage, Integer size);


    /**
     * 分页获取用户问题列表
     * @param id      用户id
     * @param current 当前页
     * @param size    每页条数
     * @return
     */
    public Pagination<QuestionDTO> questionPageByUserId(long id, Integer current, Integer size);

    /**
     * 根据id查询问题，返回questionDto对象
     * @param id 问题id
     * @return
     */
    public QuestionDTO getQuestionById(Integer id);
}
