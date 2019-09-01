package com.sourire.community.controller;

import com.sourire.community.dto.Pagination;
import com.sourire.community.dto.QuestionDTO;
import com.sourire.community.entity.User;
import com.sourire.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sourire
 * @version 1.0
 * @date 2019/9/1 14:01
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{section}")
    public String profile(@PathVariable(value = "section") String section,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(value = "current",defaultValue = "1") Integer current,
                          @RequestParam(value = "size",defaultValue = "5") Integer size){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if("questions".equals(section)){
            model.addAttribute("sectionName","我的问题");
            model.addAttribute("section","questions");
        }
        if ("replies".equals(section)) {
            model.addAttribute("sectionName","最新回复");
            model.addAttribute("section","replies");
        }

        Pagination<QuestionDTO> pagination = questionService.questionPageByUserId(user.getId(), current, size);
        model.addAttribute("pagination",pagination);
        return "profile";
    }
}
