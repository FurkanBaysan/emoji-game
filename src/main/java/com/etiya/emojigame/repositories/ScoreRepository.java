package com.etiya.emojigame.repositories;

import com.etiya.emojigame.business.dtos.responses.GetAllGameResultResponse;
import com.etiya.emojigame.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

    @Query("select s from Score as s where user_id=:userId")
    public Score getScoreOfRelatedUser(int userId);

    @Query(value = "select s.point,s.timer" +
            " from scores s " +
            "order by s.point desc, timer asc ",nativeQuery = true)
    public List<Score> getAllGameResults();
}
