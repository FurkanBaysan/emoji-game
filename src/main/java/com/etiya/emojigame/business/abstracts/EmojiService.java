package com.etiya.emojigame.business.abstracts;


import com.etiya.emojigame.entities.Emoji;

import java.util.List;

public interface EmojiService {
    public List<Emoji> getEmojisForRelatedQuestion(int questionId);

    public void save(Emoji emoji);

    public List<Emoji> getAll();
}
