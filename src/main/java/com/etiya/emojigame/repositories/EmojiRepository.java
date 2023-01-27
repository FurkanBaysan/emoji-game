package com.etiya.emojigame.repositories;

import com.etiya.emojigame.entities.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EmojiRepository extends JpaRepository<Emoji, Integer> {

    @Query("select e from Emoji as e where question_id=:questionId")
    public List<Emoji> getEmojisForRelatedQuestion(int questionId);
}
