package com.example.forum.repository;

import com.example.forum.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    Optional<Question> findById(final UUID id);
}
