package com.etiya.emojigame.repositories;

import com.etiya.emojigame.business.dtos.responses.GetAllGameResultResponse;
import com.etiya.emojigame.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

    @Query("select s from Score as s where user_id=:userId")
    public Score getScoreOfRelatedUser(int userId);


    @Query(value =  "Select new com.etiya.emojigame.business.dtos.responses.GetAllGameResultResponse( " +
            "date_part('minute',s.updatedAt) - date_part('minute', s.createdAt) ," +
            " date_part('second',s.updatedAt) - date_part('second', s.createdAt) ,  s.point, u.userName)" +
            " from Score s inner join s.user u  " +
            "  order by s.point desc, s.minute  asc  ,s.second asc ")

    public List<GetAllGameResultResponse> getAllGameResults();
}
