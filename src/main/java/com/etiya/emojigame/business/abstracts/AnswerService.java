package com.etiya.emojigame.business.abstracts;

import com.etiya.emojigame.business.dtos.requests.GetAnswerRequest;
import com.etiya.emojigame.core.utils.results.DataResult;
import com.etiya.emojigame.core.utils.results.Result;
import com.etiya.emojigame.entities.Answer;

public interface AnswerService {
    public Result getAnswer(GetAnswerRequest getAnswerRequest);
}
