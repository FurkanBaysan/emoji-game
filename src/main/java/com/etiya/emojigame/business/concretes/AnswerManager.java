package com.etiya.emojigame.business.concretes;

import com.etiya.emojigame.business.abstracts.AnswerService;
import com.etiya.emojigame.business.abstracts.QuestionService;
import com.etiya.emojigame.business.abstracts.ScoreService;
import com.etiya.emojigame.business.constants.Messages;
import com.etiya.emojigame.business.dtos.requests.GetAnswerRequest;
import com.etiya.emojigame.business.dtos.responses.GetAnswerResponse;
import com.etiya.emojigame.core.utils.messages.MessageService;
import com.etiya.emojigame.core.utils.results.*;
import com.etiya.emojigame.entities.Answer;
import com.etiya.emojigame.entities.Score;
import com.etiya.emojigame.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerManager implements AnswerService {

    private AnswerRepository answerRepository;
    private ScoreService scoreService;
    private MessageService messageService;

    @Autowired
    public AnswerManager(AnswerRepository answerRepository, ScoreService scoreService, MessageService messageService) { // DI
        this.answerRepository = answerRepository;
        this.scoreService = scoreService;
        this.messageService = messageService;
    }

    @Override
    public DataResult<GetAnswerResponse> getAnswer(GetAnswerRequest answerRequest) {

        String fixedAnswer = answerRequest.getAnswerName().replaceAll("\\s+", " ").toLowerCase();

        Answer answer = this.answerRepository.getAnswerName(answerRequest.getQuestionId(), fixedAnswer);

        Score score = new Score();

        // ilk Cevap veya yanlış cevap  ise
        if (answer == null) {
            score = this.scoreService.handleInitialOrWrongAnswer(answerRequest.getUserId());

            return new ErrorDataResult<>(getAnswerResponses(score), this.messageService.getMessage(Messages.Answer.answerWrong));
        }
        // Cevap doğru ise
        else {
            score = this.scoreService.handleCorrectAnswer(answerRequest.getUserId());

            return new SuccessDataResult<>(getAnswerResponses(score), this.messageService.getMessage(Messages.Answer.rightAnswer));

        }

    }

    @Override
    public Answer save(Answer answer) {
        Answer response = this.answerRepository.save(answer);
        return response;
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