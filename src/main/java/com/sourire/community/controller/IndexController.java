package com.sourire.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sourire.community.entity.User;
import com.sourire.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("token",token);
                User user = userService.getOne(queryWrapper);
                req.getSession().setAttribute("user",user);
            }
        }
        return "index";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
