package com.etiya.emojigame.repositories;

import com.etiya.emojigame.business.dtos.responses.GetQuestionResponse;
import com.etiya.emojigame.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("select q from Question as q where q.category=:category")
    public List<Question> getRelatedQuestionByItsCategory(String category);
}