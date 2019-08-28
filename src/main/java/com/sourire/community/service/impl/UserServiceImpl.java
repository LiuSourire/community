package com.sourire.community.service.impl;

import com.sourire.community.entity.User;
import com.sourire.community.mapper.UserMapper;
import com.sourire.community.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sourire
 * @since 2019-08-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
