package com.etiya.emojigame.repositories;

import com.etiya.emojigame.entities.Answer;
import com.etiya.emojigame.entities.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query(value = "select a from Answer as a where question_id=:questionId")
    public Answer getAnswerById(int questionId);

    @Query(value = "select a from Answer as a where name=:answerName")
    public Answer getAnswerByAnswerName(String answerName);

    @Query("select a from Answer as a where question_id=:questionId and name=:answerName")
    public Answer getAnswerName(int questionId,String answerName);

}