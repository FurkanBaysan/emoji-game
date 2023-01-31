package com.etiya.emojigame.business.abstracts;

import com.etiya.emojigame.business.constants.Messages;
import com.etiya.emojigame.entities.Score;

public interface ScoreService {
    public Score calculateScore(int userId);
    public void addScore();
}
