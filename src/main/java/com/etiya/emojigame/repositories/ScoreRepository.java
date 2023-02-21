package com.etiya.emojigame.repositories;

import com.etiya.emojigame.business.dtos.responses.GetAllGameResultResponse;
import com.etiya.emojigame.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

    @Query("select s from Score as s where user_id=:userId")
    public Score getScoreOfRelatedUser(int userId);


    @Query(value = "Select new com.etiya.emojigame.business.dtos.responses.GetAllGameResultResponse( " +

            "  round (date_part('epoch', s.updatedAt ) - date_part('epoch', u.createdAt )   ), s.point, u.userName)" +
            " from Score s inner join s.user u  " +
            "  order by s.point desc  , (date_part('epoch', s.updatedAt ) - date_part('epoch', u.createdAt )) asc")

    public List<GetAllGameResultResponse> getAllGameResults();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from scores", nativeQuery = true)
    public void deleteAllScoreRecords();
}