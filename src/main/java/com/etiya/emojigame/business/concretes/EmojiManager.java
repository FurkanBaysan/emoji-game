package com.etiya.emojigame.business.concretes;

import com.etiya.emojigame.business.abstracts.EmojiService;
import com.etiya.emojigame.entities.Emoji;
import com.etiya.emojigame.repositories.EmojiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmojiManager implements EmojiService {

    private EmojiRepository emojiRepository;

    public EmojiManager(EmojiRepository emojiRepository) {
        this.emojiRepository = emojiRepository;
    }

    @Override
    public List<Emoji> getEmojisForRelatedQuestion(int questionId) {
        return this.emojiRepository.getEmojisForRelatedQuestion(questionId);
    }

    @Override
    public void save(Emoji emoji) {
        this.emojiRepository.save(emoji);
    }

    @Override
    public List<Emoji> getAllEmojis() {
        return this.emojiRepository.findAll();
    }
}
