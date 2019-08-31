package com.sourire.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sourire.community.dto.QuestionDTO;
import com.sourire.community.entity.User;
import com.sourire.community.service.QuestionService;
import com.sourire.community.service.UserService;
import com.sourire.community.util.GetUserByCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest req, Model model){
        Cookie[] cookies = req.getCookies();
        User user = null;
        if(cookies != null && cookies.length >0){
            user = GetUserByCookie.getUserByCookie(cookies);
        }
        req.getSession().setAttribute("user",user);

        List<QuestionDTO> list = questionService.getList();
        model.addAttribute("questions",list);
        return "index";
    }

}
