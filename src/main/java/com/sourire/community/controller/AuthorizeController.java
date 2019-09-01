package com.sourire.community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sourire.community.dto.AccessTokenDTO;
import com.sourire.community.dto.GithubUser;
import com.sourire.community.entity.User;
import com.sourire.community.provider.GithubProvider;
import com.sourire.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect.url}")
    private String redirect_url;

    @Autowired
    private UserService userService;

    @RequestMapping("/callback")
    public String callback(String code, String state, HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_url);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        //持久化登录态,如果获取到的github用户不为空，就保存到数据库里，并将token写入到cookie
        if(null != githubUser){
            User user = userService.saveOrUpdate(githubUser);
            response.addCookie(new Cookie("token",user.getToken()));
        }
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req,HttpServletResponse response){
        //删除session中的user
        req.getSession().removeAttribute("user");
        //设置cookie失效
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
