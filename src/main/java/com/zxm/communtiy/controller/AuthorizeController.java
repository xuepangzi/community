package com.zxm.communtiy.controller;


import com.zxm.communtiy.dto.AccessTokenDto;
import com.zxm.communtiy.dto.GitHubUser;
import com.zxm.communtiy.mapper.UserMapper;
import com.zxm.communtiy.model.User;
import com.zxm.communtiy.provider.GitHubProvider;
import com.zxm.communtiy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.uri}")
    private String clientUri;
    @Autowired
    private UserService userService;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response){
        AccessTokenDto accessTokenDto =new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri(clientUri);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDto);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);
        if(gitHubUser != null && gitHubUser.getId() != null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            /*获取用户登录的信息*/
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccoundId(String.valueOf(gitHubUser.getId()));
            user.setAvatarUrl(gitHubUser.getAvatar_url());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
