package com.sourire.community.controller;

import com.sourire.community.dto.Pagination;
import com.sourire.community.dto.QuestionDTO;
import com.sourire.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "current",defaultValue = "1") Integer current,
                        @RequestParam(value = "size",defaultValue = "5") Integer size){
        Pagination<QuestionDTO> pagination = questionService.questionPage(current, size);
        model.addAttribute("pagination",pagination);
        return "index";
    }

}
