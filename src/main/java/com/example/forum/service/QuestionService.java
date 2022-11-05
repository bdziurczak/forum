package com.example.forum.service;

import com.example.forum.model.Question;
import com.example.forum.repository.QuestionRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionService {
    final private QuestionRepository questionRepository;


    public QuestionService(@Autowired QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void saveQuestion(Question question) {
        parseQuestionDescription(question, "\r\n", "<br/>");
        questionRepository.save(question);
    }
    public void removeQuestion(Question question) {
        questionRepository.delete(question);
    }
    public Iterable<Question> fetchAllQuestions() {

        @NotNull
        Iterable<Question> questions = questionRepository.findAll();
        questions.forEach(question ->
                                  parseQuestionDescription(question,"<br/>", " "));
        return questions;
    }

    public Question fetchQuestionById(UUID id) {
        Optional<Question> question = questionRepository.findById(id);
        if(question.isEmpty()) return new Question();
        return question.get();
    }
    public void parseQuestionDescription(Question q, String old, String fresh) {
        String description = q.getDescription();
        description = description.replaceAll(old, fresh);
        q.setDescription(description);
    }
}
