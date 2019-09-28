package com.sourire.community.controller;


import com.sourire.community.dto.CommentDTO;
import com.sourire.community.dto.QuestionDTO;
import com.sourire.community.entity.Question;
import com.sourire.community.entity.User;
import com.sourire.community.enums.CommentTypeEnum;
import com.sourire.community.exception.AppException;
import com.sourire.community.exception.AppExceptionCode;
import com.sourire.community.service.CommentService;
import com.sourire.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
    private CommentService commentService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question, Model model, HttpServletRequest req) {
        model.addAttribute("question", question);
        //判断表单值不能为空
        if (null == question.getTitle() || "".equals(question.getTitle())) {
            model.addAttribute("error", "标题不能为空！");
            return "publish";
        }
        if (null == question.getDescription() || "".equals(question.getDescription())) {
            model.addAttribute("error", "问题补充不能为空！");
            return "publish";
        }
        if (null == question.getTag() || "".equals(question.getTag())) {
            model.addAttribute("error", "标签不能为空！");
            return "publish";
        }
        try {
            question.setGmtCreate(new Date());
            User user = (User) req.getSession().getAttribute("user");
            if (null == user) {
                return "redirect:/";
            }
            question.setCreator(user.getId());
            questionService.saveOrUpdate(question);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "publish";
        }
    }

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        //增加浏览数
        questionService.incViewCount(questionDTO.getId());
        //加载评论列表
        List<CommentDTO> comments= commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //加载相关问题
        List<QuestionDTO> relatedQuestions =  questionService.listRelatedByTags(questionDTO);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }

    @GetMapping("/publish/{id}")
    public String editQuestion(@PathVariable("id") Integer id,
                               Model model) {
        Question question = questionService.getById(id);
        if (question == null) {
            throw new AppException(AppExceptionCode.QUESTION_NOT_FOUND);
        }
        model.addAttribute("question", question);
        return "publish";
    }

}

