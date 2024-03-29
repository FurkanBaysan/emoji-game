package com.etiya.emojigame.business.concretes;

import com.etiya.emojigame.business.abstracts.AnswerService;
import com.etiya.emojigame.business.abstracts.EmojiService;
import com.etiya.emojigame.business.abstracts.QuestionService;
import com.etiya.emojigame.business.constants.Messages;
import com.etiya.emojigame.business.dtos.requests.AddQuestionRequest;
import com.etiya.emojigame.business.dtos.responses.GetEmojiResponse;
import com.etiya.emojigame.business.dtos.responses.GetQuestionResponse;
import com.etiya.emojigame.core.utils.exceptions.BusinessException;
import com.etiya.emojigame.core.utils.mapping.ModelMapperService;
import com.etiya.emojigame.core.utils.messages.MessageService;
import com.etiya.emojigame.core.utils.results.DataResult;
import com.etiya.emojigame.core.utils.results.Result;
import com.etiya.emojigame.core.utils.results.SuccessDataResult;
import com.etiya.emojigame.core.utils.results.SuccessResult;
import com.etiya.emojigame.entities.Answer;
import com.etiya.emojigame.entities.Emoji;
import com.etiya.emojigame.entities.Question;
import com.etiya.emojigame.repositories.QuestionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionManager implements QuestionService {

    private QuestionRepository questionRepository;
    private EmojiService emojiService;
    private ModelMapperService modelMapperService;
    private AnswerService answerService;
    private MessageService messageService;

    public QuestionManager(QuestionRepository questionRepository, EmojiService emojiService, ModelMapperService modelMapperService, @Lazy AnswerService answerService, MessageService messageService) {
        this.questionRepository = questionRepository;
        this.emojiService = emojiService;
        this.modelMapperService = modelMapperService;
        this.answerService = answerService;
        this.messageService = messageService;
    }

    @Override
    public DataResult<List<GetEmojiResponse>> getEmojisForRelatedQuestion(int questionId) {
        List<Emoji> emojis = this.emojiService.getEmojisForRelatedQuestion(questionId);

        List<GetEmojiResponse> emojiResponses = new ArrayList<>();

        emojiResponses = emojis.stream().map(emoji -> this.modelMapperService.getMapperForResponse()
                .map(emoji, GetEmojiResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<GetEmojiResponse>>(emojiResponses,
                this.messageService.getMessage(Messages.Question.emojisForRelatedQuestionSuccessfullyFetched));
    }


    @Override
    public DataResult<List<GetQuestionResponse>> getAllQuestions() {
        List<Question> questions = this.questionRepository.findAll();

        List<GetQuestionResponse> getQuestionResponses = new ArrayList<>();

        for (Question question : questions) {

            DataResult<List<GetEmojiResponse>> emojiResponses = getEmojisForRelatedQuestion(question.getId());

            GetQuestionResponse getQuestionResponse = new GetQuestionResponse();

            getQuestionResponse.setId(question.getId());
            getQuestionResponse.setGetEmojiResponses(emojiResponses.getData());
            getQuestionResponse.setCategory(question.getCategory());

            getQuestionResponses.add(getQuestionResponse);

        }

        return new SuccessDataResult<List<GetQuestionResponse>>(getQuestionResponses,
                this.messageService.getMessage(Messages.Question.allEmojisForAllQuestionsSuccessfullyFetched));

    }

    @Override
    public List<Question> getAll() {
        return this.questionRepository.findAll();
    }

    @Override
    @Transactional
    public Result addQuestion(AddQuestionRequest addQuestionRequest) {

        Question question = new Question();
        Question savedQuestion = this.questionRepository.save(question);

        Answer answer = new Answer();
        answer.setQuestion(savedQuestion);
        answer.setName(addQuestionRequest.getAnswer());

        List<Emoji> emojis = new ArrayList<>();

        for (int i = 0; i < addQuestionRequest.getImageUrls().size(); i++) {
            Emoji emoji = new Emoji();
            emoji.setImageUrl(addQuestionRequest.getImageUrls().get(i));
            emoji.setQuestion(savedQuestion);
            emojis.add(emoji);
        }

        for (Emoji emoji : emojis) {
            this.emojiService.save(emoji);
        }

        savedQuestion.setId(savedQuestion.getId());
        savedQuestion.setEmojis(emojis);
        Answer responseAnswer = this.answerService.save(answer);
        savedQuestion.setAnswer(responseAnswer);
        savedQuestion.setCategory(addQuestionRequest.getCategory());

        this.questionRepository.save(savedQuestion);

        return new SuccessResult(this.messageService.getMessage(Messages.Question.relatedQuestionSuccessfullyAdded));

    }

    @Override
    public DataResult<List<GetQuestionResponse>> getRelatedQuestionByItsCategory(String category) {
        List<Question> questionsByTheirCategory = this.questionRepository.getRelatedQuestionByItsCategory(category);

        isQuestionExistInRelatedCategory(questionsByTheirCategory);

        List<GetQuestionResponse> questionResponses = new ArrayList<>();

        for (Question question : questionsByTheirCategory) {

            DataResult<List<GetEmojiResponse>> emojiResponses = getEmojisForRelatedQuestion(question.getId());

            GetQuestionResponse questionResponse = new GetQuestionResponse();

            questionResponse.setId(question.getId());
            questionResponse.setCategory(question.getCategory());
            questionResponse.setGetEmojiResponses(emojiResponses.getData());

            questionResponses.add(questionResponse);
        }

        return new SuccessDataResult<List<GetQuestionResponse>>(questionResponses,
                this.messageService.getMessage(Messages.Question.allQuestionAreFetchedAccordingToTheirCategory));

    }

    private void isQuestionExistInRelatedCategory(List<Question> questions) {
        if (questions.size() == 0) {
            throw new BusinessException("there is no question corresponding with this category");
        }
    }
}