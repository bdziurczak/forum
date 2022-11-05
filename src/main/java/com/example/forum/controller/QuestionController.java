package com.example.forum.controller;

import com.example.forum.model.Answer;
import com.example.forum.model.Question;
import com.example.forum.model.Tag;
import com.example.forum.service.QuestionService;
import com.example.forum.service.TagService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequestMapping(path="/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final TagService tagService;

    public QuestionController(@Autowired QuestionService questionService,
                              @Autowired TagService tagService) {
        this.questionService = questionService;
        this.tagService = tagService;
    }


    @GetMapping
    public String dashboard(Model model) {
        Iterable<Question> questions = questionService.fetchAllQuestions();

        model.addAttribute("questions", questions);
        return "dashboard";
    }


    @GetMapping(path="/add")
    public String addQuestionForm(Model model) {
        Question question = new Question();
        Iterable<Tag> tags = tagService.returnTags();

        model.addAttribute("question", question);
        model.addAttribute("tags", tags);
        return "question/add_question";
    }

    @PostMapping(path="/add")
    public String addQuestion
            (@ModelAttribute @NotNull Question question, Model model) {
        questionService.saveQuestion(question);

        model.addAttribute("question", question);
        return "question/add_question_result";
    }

    @GetMapping("/remove")
    public String removeQuestionForm(Model model) {
        Iterable<Question> questions = questionService.fetchAllQuestions();
        model.addAttribute("questions", questions);
        return "question/remove_question";
    }

    @GetMapping("/remove/{id}")
    public String removeQuestion(@PathVariable UUID id, Model model) throws Exception {
        Question question = questionService.fetchQuestionById(id);
        questionService.removeQuestion(question);
        model.addAttribute("question", question);
        return "question/remove_question_result";
    }

    @GetMapping("/details/{id}")
    public String detailsQuestionById(@PathVariable UUID id, Model model) throws Exception {
        Question question = questionService.fetchQuestionById(id);

        model.addAttribute("question", question);
        model.addAttribute("tags", question.getTags());

        model.addAttribute("answer", new Answer());
        model.addAttribute("answers", question.getAnswers());

        return "question/details";
    }

    //@GetMapping("/search")
    /*public ModelAndView searchQuestion() {

    }*/
}
