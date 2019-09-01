package com.sourire.community.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sourire.community.entity.User;
import com.sourire.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sourire
 * @version 1.0
 * @date 2019/9/1 13:39
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userService.getOne(new QueryWrapper<User>().eq("token", token));
                    request.getSession().setAttribute("user",user);
                }
            }
        }
        return true;
    }
}
