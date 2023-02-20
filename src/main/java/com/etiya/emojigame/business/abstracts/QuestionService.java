package com.etiya.emojigame.business.abstracts;

import com.etiya.emojigame.business.dtos.requests.AddQuestionRequest;
import com.etiya.emojigame.business.dtos.responses.GetEmojiResponse;
import com.etiya.emojigame.business.dtos.responses.GetQuestionResponse;
import com.etiya.emojigame.core.utils.results.DataResult;
import com.etiya.emojigame.core.utils.results.Result;
import com.etiya.emojigame.entities.Question;

import java.util.List;

public interface QuestionService {

    public DataResult<List<GetEmojiResponse>> getEmojisForRelatedQuestion(int questionId);

    public Result addQuestion(AddQuestionRequest addQuestionRequest);

    public DataResult<List<GetQuestionResponse>> getAllQuestions();

    public List<Question> getAll();

    public DataResult<List<GetQuestionResponse>> getRelatedQuestionByItsCategory(String category);
}
