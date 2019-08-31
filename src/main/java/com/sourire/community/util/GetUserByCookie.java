package com.sourire.community.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sourire.community.entity.User;
import com.sourire.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;

@Component
public class GetUserByCookie {

    private static UserService userService;

    @Autowired
    private UserService userServiceImpl;

    @PostConstruct
    public void init() {
        userService = userServiceImpl;

    }

    public static User getUserByCookie(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                return userService.getOne(new QueryWrapper<User>().eq("token", token));
            }
        }
        return null;
    }
}
