package com.example.forum.service;

import com.example.forum.model.Answer;
import com.example.forum.model.Question;
import com.example.forum.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;


    public AnswerService(@Autowired AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void save(Answer answer, Question question) {
        parseAnswerContent(answer);
        question.addAnswer(answer);
        answerRepository.save(answer);
    }

    private void parseAnswerContent(Answer a) {
        String content = a.getContent();
        content = content.replaceAll("\r\n", "<br/>");
        a.setContent(content);
    }
}
