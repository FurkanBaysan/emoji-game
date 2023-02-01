package com.etiya.emojigame.repositories;

import com.etiya.emojigame.business.dtos.responses.GetAllGameResultResponse;
import com.etiya.emojigame.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

    @Query("select s from Score as s where user_id=:userId")
    public Score getScoreOfRelatedUser(int userId);

    @Query("select new com.etiya.emojigame.business.dtos.responses.GetAllGameResultResponse(s.point,u.userName,s.totalTime) " +
            "From Score as s " +
            "inner join User as u on s.user=u " +
            " order by s.point desc, s.totalTime asc")
    public List<GetAllGameResultResponse> getAllGameResults();
}
