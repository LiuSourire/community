package com.sourire.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sourire.community.entity.Question;
import com.sourire.community.entity.User;
import com.sourire.community.service.QuestionService;
import com.sourire.community.service.UserService;
import com.sourire.community.util.GetUserByCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sourire
 * @since 2019-08-28
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question, Model model, HttpServletRequest req){
        model.addAttribute("question",question);
        //判断表单值不能为空
        if (null == question.getTitle() || "".equals(question.getTitle())) {
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }
        if (null == question.getDescription() || "".equals(question.getDescription())) {
            model.addAttribute("error","问题补充不能为空！");
            return "publish";
        }
        if (null == question.getTag() || "".equals(question.getTag())) {
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }
        try {
            question.setGmtCreate(new Date());
            Cookie[] cookies = req.getCookies();
            User user = null;
            if(cookies != null && cookies.length >0){
                user = GetUserByCookie.getUserByCookie(cookies);
            }
            question.setCreator(user.getId());
            questionService.save(question);
            return "redirect:/";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "publish";
        }
    }

}

