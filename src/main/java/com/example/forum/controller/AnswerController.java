package com.example.forum.controller;

import com.example.forum.model.Answer;
import com.example.forum.model.Question;
import com.example.forum.service.AnswerService;
import com.example.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class AnswerController {
    final private AnswerService answerService;
    final private QuestionService questionService;

    public AnswerController(@Autowired AnswerService answerService,
                            @Autowired QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @PostMapping("/answers/{questionid}/add")
    public String addAnswer(@ModelAttribute Answer answer, @PathVariable UUID questionid)
            throws Exception {
        Question question = questionService.fetchQuestionById(questionid);
        answerService.save(answer, question);
        return "redirect:/questions/details/{questionid}";
    }
}
