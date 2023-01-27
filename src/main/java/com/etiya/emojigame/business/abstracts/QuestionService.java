package com.etiya.emojigame.business.abstracts;

import com.etiya.emojigame.business.dtos.requests.AddEmojiRequest;
import com.etiya.emojigame.business.dtos.responses.GetEmojiResponse;
import com.etiya.emojigame.business.dtos.responses.GetQuestionResponse;
import com.etiya.emojigame.core.utils.results.DataResult;
import com.etiya.emojigame.core.utils.results.Result;
import com.etiya.emojigame.entities.Question;

import java.util.List;

public interface QuestionService {

    public DataResult<List<GetEmojiResponse>> getEmojisForRelatedQuestion(int questionId);

    public Result addQuestion(AddEmojiRequest addEmojiRequest);

    public DataResult<List<GetQuestionResponse>> getAllQuestions();

    public List<Question> getAll();
}
