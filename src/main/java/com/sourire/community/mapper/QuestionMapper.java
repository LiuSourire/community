package com.sourire.community.mapper;

import com.sourire.community.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Select("select * from question order by id desc limit ${offset},${size} ")
    List<Question> selectListByPage(@Param(value = "offset") Integer offset , @Param(value = "size") Integer size);

    /**
     * 分页查询（千条记录之外，使用此分页查询方法）
     * @param offset 偏移量
     * @param size 查询条数
     * @return
     */
    @Select("select * from question where id > (select id from question limit ${offset},1) order by id desc limit ${size}")
    List<Question> selectListByPageEffcient(@Param(value = "offset") Integer offset , @Param(value = "size") Integer size);

    /**
     * 分页查询（千条记录之外，使用此分页查询方法）
     * @param offset 偏移量
     * @param size 查询条数
     * @return
     */
    @Select("select * from question where creator = ${id} and id > (select id from question where creator = ${id} limit ${offset},1) order by id desc limit ${size}")
    List<Question> selectListPageByUserIdEffcient(@Param(value = "id") long id, @Param(value = "offset") Integer offset , @Param(value = "size") Integer size);

    /**
     * 分页查询（千条记录内，使用此分页查询方法）
     * @param offset 偏移量
     * @param size 查询条数
     * @return
     */
    @Select("select * from question where creator = ${id} order by id desc limit ${offset},${size}")
    List<Question> selectListPageByUserId(@Param(value = "id") long id, @Param(value = "offset") Integer offset , @Param(value = "size") Integer size);

    @Update("update question set view_count = view_count + 1 where id=${id}")
    void updateViewCount(@Param(value = "id") Integer id);

    @Update("update question set comment_count = comment_count +1 where id = ${id}")
    void increaseCommentCount(@Param(value = "id") Integer id);

    @Select("select * from question where tag regexp '${tag}' and id != ${id}")
    List<Question> listRelatedByTags(@Param(value = "id") Integer id, @Param(value = "tag") String regexpTags);
}
