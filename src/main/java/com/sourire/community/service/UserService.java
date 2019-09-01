package com.sourire.community.service;

import com.sourire.community.dto.GithubUser;
import com.sourire.community.dto.QuestionDTO;
import com.sourire.community.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sourire
 * @since 2019-08-27
 */
@Service
public interface UserService extends IService<User> {

    /**
     * 根据github授权登录的用户信息，进行saveorupdate操作
     * @param entity
     * @return
     */
    public User saveOrUpdate(GithubUser entity);
}
