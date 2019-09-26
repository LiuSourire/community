package com.sourire.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sourire.community.dto.GithubUser;
import com.sourire.community.entity.User;
import com.sourire.community.mapper.UserMapper;
import com.sourire.community.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

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
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public User saveOrUpdate(GithubUser entity) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("account_id", entity.getId()));
        //如果该用户在数据库中不存在，则新增
        if(user == null){
            user = new User();
            user.setName(entity.getLogin());
            user.setAvatarUrl(entity.getAvatarUrl());
            user.setToken(UUID.randomUUID().toString());
            user.setAccountId(entity.getId());
            user.setGmtCreate(new Date());
            userMapper.insert(user);
        }else{
            user.setName(entity.getLogin());
            user.setAvatarUrl(entity.getAvatarUrl());
            user.setToken(UUID.randomUUID().toString());
            user.setGmtModify(new Date());
            userMapper.update(user,new QueryWrapper<User>().eq("account_id",user.getAccountId()));
        }
        return user;
    }
}
