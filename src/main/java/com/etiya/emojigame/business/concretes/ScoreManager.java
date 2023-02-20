package com.etiya.emojigame.business.concretes;

import com.etiya.emojigame.business.abstracts.AnswerService;
import com.etiya.emojigame.business.abstracts.ScoreService;
import com.etiya.emojigame.business.constants.Enums;
import com.etiya.emojigame.business.constants.Messages;
import com.etiya.emojigame.business.dtos.responses.GetAllGameResultResponse;
import com.etiya.emojigame.core.utils.messages.MessageService;
import com.etiya.emojigame.core.utils.results.DataResult;
import com.etiya.emojigame.core.utils.results.SuccessDataResult;
import com.etiya.emojigame.entities.Score;
import com.etiya.emojigame.entities.User;
import com.etiya.emojigame.repositories.ScoreRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
@EnableScheduling
public class ScoreManager implements ScoreService {
    private ScoreRepository scoreRepository;
    private MessageService messageService;

    public ScoreManager(ScoreRepository scoreRepository, MessageService messageService) {
        this.scoreRepository = scoreRepository;
        this.messageService = messageService;
    }

    @Override
    public Score handleCorrectAnswer(int userId) {

        Score savedScore = handleInitialOrWrongAnswer(userId);
        savedScore.setPoint(savedScore.getPoint() + Enums.increasePoint);
        savedScore.setNumberOfCorrectAnswer(savedScore.getPoint() / 20);
        return this.scoreRepository.save(savedScore);
    }

    @Override
    public Score handleInitialOrWrongAnswer(int userId) {
        Score currentScore = this.scoreRepository.getScoreOfRelatedUser(userId);
        //Eğer Cevap yanlış ise
        if (currentScore != null) {
            currentScore.setUpdatedAt(LocalDateTime.now());
            return this.scoreRepository.save(currentScore);
        }
        //Eğer ilk cevap ise
        else {
            User newUser = new User();
            newUser.setId(userId);

            Score newUserScore = new Score();
            newUserScore.setUser(newUser);
            newUserScore.setPoint(Enums.initialPoint);
            newUserScore.setNumberOfCorrectAnswer(Enums.initialPoint);
            newUserScore.setUpdatedAt(LocalDateTime.now());
            return this.scoreRepository.save(newUserScore);
        }

    }

    @Override
    public DataResult<List<GetAllGameResultResponse>> getAllGameResult() {
        List<GetAllGameResultResponse> allGameResults = this.scoreRepository.getAllGameResults();

        return new SuccessDataResult<List<GetAllGameResultResponse>>(allGameResults,
                this.messageService.getMessage(Messages.User.usersAreListedAccordingToTheirPointsAndTimes));
    }

    public void saveScore(Score score) {
        this.scoreRepository.save(score);
    }

    @Override
    // @Scheduled(cron = "0 27 16 ? * *")
    public void job() {
        //this.logger.info("Log Current Time: " + new Date());

        Score score = new Score();

        //this.scoreRepository.deleteAllScoreRecords();


    }

}

