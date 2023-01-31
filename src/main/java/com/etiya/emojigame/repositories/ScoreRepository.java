package com.etiya.emojigame.repositories;

import com.etiya.emojigame.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

    @Query("select s from Score as s where user_id=:userId")
    public Score getScoreOfRelatedUser(int userId);
}
