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


        String resultString = answerRequest.getAnswerName().replaceAll("\\s+", " ").toLowerCase();

        Answer answer = this.answerRepository.getAnswerName(answerRequest.getQuestionId(), resultString);
        GetAnswerResponse getAnswerResponse=new GetAnswerResponse();

        if (answer == null) {
           getAnswerResponse.setNumberOfCorrectAnswer(0);
           getAnswerResponse.setPoint(0);
            return new ErrorDataResult<>(getAnswerResponse,Messages.Answer.answerWrong);
        } else {
            Score score= this.scoreService.calculateScore(answerRequest.getUserId());

            getAnswerResponse.setPoint(score.getPoint());
            getAnswerResponse.setNumberOfCorrectAnswer(score.getNumberOfCorrectAnswer());
            getAnswerResponse.setUserId(score.getUser().getId());
            return new SuccessDataResult<>(getAnswerResponse,Messages.Answer.rightAnswer);
        }


    }

    public Result checkIfQuestionIsExist(int questionId) {

        Answer currentAnswer = this.answerRepository.getAnswerById(questionId);

        if (currentAnswer == null) {
            return new ErrorResult(Messages.Answer.questionNotExist);
        } else {
            return new SuccessResult(Messages.Answer.rightAnswer);
        }

    }

    public Result checkIfAnswerNameExist(String answerName) {

        Answer currentAnswer = this.answerRepository.getAnswerByAnswerName(answerName);

        if (currentAnswer == null) {
            return new ErrorResult(Messages.Answer.answerWrong);
        } else {
            return new SuccessResult(Messages.Answer.rightAnswer);
        }

    }

}
