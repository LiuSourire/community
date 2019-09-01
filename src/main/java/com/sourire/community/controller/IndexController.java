package com.sourire.community.controller;

import com.sourire.community.dto.Pagination;
import com.sourire.community.dto.QuestionDTO;
import com.sourire.community.entity.User;
import com.sourire.community.service.QuestionService;
import com.sourire.community.service.UserService;
import com.sourire.community.util.GetUserByCookie;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String index(HttpServletRequest req,
                        Model model,
                        @RequestParam(value = "current",defaultValue = "1") Integer current,
                        @RequestParam(value = "size",defaultValue = "5") Integer size){
        Cookie[] cookies = req.getCookies();
        User user = null;
        if(cookies != null && cookies.length >0){
            user = GetUserByCookie.getUserByCookie(cookies);
        }
        req.getSession().setAttribute("user",user);

        Pagination<QuestionDTO> pagination = questionService.questionPage(current, size);

        model.addAttribute("pagination",pagination);
        return "index";
    }

}
