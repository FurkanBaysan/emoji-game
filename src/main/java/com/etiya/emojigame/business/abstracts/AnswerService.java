package com.etiya.emojigame.business.abstracts;

import com.etiya.emojigame.business.dtos.requests.GetAnswerRequest;
import com.etiya.emojigame.business.dtos.responses.GetAnswerResponse;
import com.etiya.emojigame.core.utils.results.DataResult;
import com.etiya.emojigame.core.utils.results.Result;
import com.etiya.emojigame.entities.Answer;

public interface AnswerService {
    public DataResult<GetAnswerResponse> getAnswer(GetAnswerRequest getAnswerRequest);

    public Answer save(Answer answer);

}
