package com.etiya.emojigame.business.concretes;

import com.etiya.emojigame.business.abstracts.AnswerService;
import com.etiya.emojigame.business.abstracts.QuestionService;
import com.etiya.emojigame.business.constants.Messages;
import com.etiya.emojigame.business.dtos.requests.GetAnswerRequest;
import com.etiya.emojigame.core.utils.results.ErrorResult;
import com.etiya.emojigame.core.utils.results.Result;
import com.etiya.emojigame.core.utils.results.SuccessResult;
import com.etiya.emojigame.entities.Answer;
import com.etiya.emojigame.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AnswerManager implements AnswerService {

    private AnswerRepository answerRepository;
    private QuestionService questionService;

    @Autowired
    public AnswerManager(AnswerRepository answerRepository, QuestionService questionService) { // DI
        this.answerRepository = answerRepository;
        this.questionService = questionService;
    }

    @Override
    public Result getAnswer(GetAnswerRequest answerRequest) {


        String resultString= answerRequest.getAnswerName().replaceAll("\\s+"," ").toLowerCase();
        System.out.println(resultString);

        Answer answer = this.answerRepository.getAnswerName(answerRequest.getQuestionId(), resultString);



        if(answer==null){
            return new ErrorResult(Messages.Answer.answerWrong);
        }
        else {
            return new SuccessResult(Messages.Answer.rightAnswer);
        }


    }

    public Result checkIfQuestionIsExist(int questionId){

        Answer currentAnswer= this.answerRepository.getAnswerById(questionId);

        if(currentAnswer == null){
            return new ErrorResult(Messages.Answer.questionNotExist);
        }
        else {
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
