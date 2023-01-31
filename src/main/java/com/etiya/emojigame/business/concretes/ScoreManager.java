package com.etiya.emojigame.business.concretes;

import com.etiya.emojigame.business.abstracts.AnswerService;
import com.etiya.emojigame.business.abstracts.ScoreService;
import com.etiya.emojigame.business.constants.Enums;
import com.etiya.emojigame.entities.Score;
import com.etiya.emojigame.entities.User;
import com.etiya.emojigame.repositories.ScoreRepository;
import org.springframework.stereotype.Service;


@Service
public class ScoreManager implements ScoreService {
    private ScoreRepository scoreRepository;

    public ScoreManager(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public Score calculateScore(int userId) {

        Score score = this.scoreRepository.getScoreOfRelatedUser(userId);

        if (score != null) {

            score.setPoint(score.getPoint() + Enums.increasePoint);
            this.scoreRepository.save(score);
            return score;
        }

        User user = new User();
        user.setId(userId);

        Score newUserScore = new Score();
        newUserScore.setUser(user);
        newUserScore.setPoint(Enums.initialPoint);

        Score savedScore = this.scoreRepository.save(newUserScore);

        savedScore.setPoint(savedScore.getPoint() + Enums.increasePoint);
        savedScore.setUser(user);

        return this.scoreRepository.save(savedScore);

    }

    @Override
    public void addScore() {

    }

}
