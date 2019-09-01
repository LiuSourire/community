package com.sourire.community.mapper;

import com.sourire.community.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 分页查询（千条记录内，使用此分页查询方法）
     * @param offset 偏移量
     * @param size 查询条数
     * @return
     */
    @Select("select * from question limit ${offset},${size}")
    List<Question> selectListByPage(@Param(value = "offset") Integer offset , @Param(value = "size") Integer size);

    /**
     * 分页查询（千条记录之外，使用此分页查询方法）
     * @param offset 偏移量
     * @param size 查询条数
     * @return
     */
    @Select("select * from question where id > (select id from question limit ${offset},1) limit ${size}")
    List<Question> selectListByPageEffcient(@Param(value = "offset") Integer offset , @Param(value = "size") Integer size);
}