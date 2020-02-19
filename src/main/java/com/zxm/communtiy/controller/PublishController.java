package com.zxm.communtiy.controller;

import com.zxm.communtiy.dto.QuestionDTO;
import com.zxm.communtiy.mapper.QuestionMapper;
import com.zxm.communtiy.model.Question;
import com.zxm.communtiy.model.User;
import com.zxm.communtiy.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/publish/{id}")
    public String editPublish(
            @PathVariable(name = "id") Integer id,
            Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("title",questionDTO.getTitle());
        model.addAttribute("description",questionDTO.getDescription());
        model.addAttribute("tag",questionDTO.getTag());
        model.addAttribute("id",questionDTO.getId());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name="id") Integer id,
                            @RequestParam(name="title") String title,
                            @RequestParam(name="description") String description,
                            @RequestParam(name="tag") String tag,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }else{
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setId(id);
            questionService.createOrUpdate(question);
            return "redirect:/";
        }
    }
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

}
