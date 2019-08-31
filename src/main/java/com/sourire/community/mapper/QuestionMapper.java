package com.sourire.community.mapper;

import com.sourire.community.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sourire
 * @since 2019-08-28
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}
