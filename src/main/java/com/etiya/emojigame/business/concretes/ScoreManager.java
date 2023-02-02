package com.etiya.emojigame.business.concretes;

import com.etiya.emojigame.business.abstracts.AnswerService;
import com.etiya.emojigame.business.abstracts.ScoreService;
import com.etiya.emojigame.business.constants.Enums;
import com.etiya.emojigame.business.constants.Messages;
import com.etiya.emojigame.business.dtos.responses.GetAllGameResultResponse;
import com.etiya.emojigame.core.utils.results.DataResult;
import com.etiya.emojigame.core.utils.results.SuccessDataResult;
import com.etiya.emojigame.entities.Score;
import com.etiya.emojigame.entities.User;
import com.etiya.emojigame.repositories.ScoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ScoreManager implements ScoreService {
    private ScoreRepository scoreRepository;

    public ScoreManager(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public Score handleCorrectAnswer(int userId) {

        Score savedScore = handleInitialOrWrongAnswer(userId);
        savedScore.setPoint(savedScore.getPoint() + Enums.increasePoint);
        savedScore.setNumberOfCorrectAnswer(savedScore.getPoint() / 20);
        return this.scoreRepository.save(savedScore);

    }

    @Override
    public DataResult<List<GetAllGameResultResponse>> getAllGameResult() {
        List<GetAllGameResultResponse> allGameResults = this.scoreRepository.getAllGameResults();

        return new SuccessDataResult<List<GetAllGameResultResponse>>(allGameResults,
                Messages.User.usersAreListedAccordingToTheirPoints);
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
            return this.scoreRepository.save(newUserScore);
        }

    }

}
