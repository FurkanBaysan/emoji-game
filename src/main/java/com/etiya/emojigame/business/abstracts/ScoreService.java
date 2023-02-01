package com.etiya.emojigame.business.abstracts;

import com.etiya.emojigame.business.constants.Messages;
import com.etiya.emojigame.business.dtos.responses.GetAllGameResultResponse;
import com.etiya.emojigame.core.utils.results.DataResult;
import com.etiya.emojigame.entities.Score;

import java.util.List;

public interface ScoreService {
    public Score calculateScore(int userId);

    public DataResult<List<GetAllGameResultResponse>> getAllGameResult();
}
