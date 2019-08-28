package com.sourire.community.mapper;

import com.sourire.community.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sourire
 * @since 2019-08-27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
