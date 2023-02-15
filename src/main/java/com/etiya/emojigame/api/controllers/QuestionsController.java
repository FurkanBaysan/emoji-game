package com.etiya.emojigame.api.controllers;

import com.etiya.emojigame.business.abstracts.QuestionService;
import com.etiya.emojigame.business.constants.Paths;
import com.etiya.emojigame.business.dtos.requests.AddEmojiRequest;
import com.etiya.emojigame.business.dtos.responses.GetEmojiResponse;
import com.etiya.emojigame.business.dtos.responses.GetQuestionResponse;
import com.etiya.emojigame.core.utils.results.DataResult;
import com.etiya.emojigame.core.utils.results.Result;
import com.etiya.emojigame.entities.Question;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "questions/")
@CrossOrigin(origins = "http://localhost:8080/")
public class QuestionsController {
    private QuestionService questionService;

    public QuestionsController(QuestionService questionService) { // DI
        this.questionService = questionService;
    }

    @GetMapping("get-emojis-for-related-question")
    public DataResult<List<GetEmojiResponse>> getEmojisForRelatedQuestion(@RequestParam int questionId) {
        return this.questionService.getEmojisForRelatedQuestion(questionId);
    }

    @PostMapping("add-question")
    public Result addQuestion(@RequestBody AddEmojiRequest addEmojiRequest) {
        return this.questionService.addQuestion(addEmojiRequest);
    }

    @GetMapping("get-all-questions")
    public DataResult<List<GetQuestionResponse>> getAllQuestions() {
        return this.questionService.getAllQuestions();
    }

}
