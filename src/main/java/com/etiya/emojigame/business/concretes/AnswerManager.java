package com.etiya.emojigame.business.concretes;

import com.etiya.emojigame.business.abstracts.AnswerService;
import com.etiya.emojigame.business.abstracts.QuestionService;
import com.etiya.emojigame.business.abstracts.ScoreService;
import com.etiya.emojigame.business.constants.Messages;
import com.etiya.emojigame.business.dtos.requests.GetAnswerRequest;
import com.etiya.emojigame.business.dtos.responses.GetAnswerResponse;
import com.etiya.emojigame.core.utils.results.*;
import com.etiya.emojigame.entities.Answer;
import com.etiya.emojigame.entities.Score;
import com.etiya.emojigame.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerManager implements AnswerService {

    private AnswerRepository answerRepository;
    private QuestionService questionService;

    private ScoreService scoreService;

    @Autowired
    public AnswerManager(AnswerRepository answerRepository, QuestionService questionService, ScoreService scoreService) { // DI
        this.answerRepository = answerRepository;
        this.questionService = questionService;
        this.scoreService = scoreService;
    }

    @Override
    public DataResult<GetAnswerResponse> getAnswer(GetAnswerRequest answerRequest) {

        String fixedAnswer = answerRequest.getAnswerName().replaceAll("\\s+", " ").toLowerCase();

        Answer answer = this.answerRepository.getAnswerName(answerRequest.getQuestionId(), fixedAnswer);

        Score score = new Score();

        // Cevap yanlış ise
        if (answer == null) {
            score = this.scoreService.handleInitialOrWrongAnswer(answerRequest.getUserId());
           /* getAnswerResponse.setCreatedAt(score.getCreatedAt());
            getAnswerResponse.setPoint(score.getPoint());
            getAnswerResponse.setNumberOfCorrectAnswer(score.getNumberOfCorrectAnswer());
            getAnswerResponse.setUserId(score.getUser().getId());
            getAnswerResponse.setUpdatedAt(score.getUpdatedAt());*/

            return new ErrorDataResult<>(getAnswerResponses(score), Messages.Answer.answerWrong);
        }
        // Cevap doğru ise
        else {
            score = this.scoreService.handleCorrectAnswer(answerRequest.getUserId());
  /*          getAnswerResponse.setCreatedAt(score.getCreatedAt());
            getAnswerResponse.setPoint(score.getPoint());
            getAnswerResponse.setNumberOfCorrectAnswer(score.getNumberOfCorrectAnswer());
            getAnswerResponse.setUserId(score.getUser().getId());
            getAnswerResponse.setUpdatedAt(score.getUpdatedAt());*/

            return new SuccessDataResult<>(getAnswerResponses(score), Messages.Answer.rightAnswer);


        }

    }

    //Builder
    private GetAnswerResponse getAnswerResponses(Score score) {
        return GetAnswerResponse.builder()
                .userId(score.getUser().getId())
                .point(score.getPoint())
                .numberOfCorrectAnswer(score.getNumberOfCorrectAnswer())
                .createdAt(score.getCreatedAt())
                .updatedAt(score.getUpdatedAt())
                .build();
    }


}
