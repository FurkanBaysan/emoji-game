package com.etiya.emojigame.api.controllers;

import com.etiya.emojigame.business.abstracts.QuestionService;
import com.etiya.emojigame.business.constants.Paths;
import com.etiya.emojigame.business.dtos.requests.AddQuestionRequest;
import com.etiya.emojigame.business.dtos.responses.GetEmojiResponse;
import com.etiya.emojigame.business.dtos.responses.GetQuestionResponse;
import com.etiya.emojigame.core.utils.results.DataResult;
import com.etiya.emojigame.core.utils.results.Result;
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
    public Result addQuestion(@RequestBody AddQuestionRequest addQuestionRequest) {
        return this.questionService.addQuestion(addQuestionRequest);
    }

    @GetMapping("get-all-questions")
    public DataResult<List<GetQuestionResponse>> getAllQuestions() {
        return this.questionService.getAllQuestions();
    }

    @GetMapping("get-related-question-by-its-category")
    public DataResult<List<GetQuestionResponse>> getRelatedQuestionByItsCategory(@RequestParam String category) {
        return this.questionService.getRelatedQuestionByItsCategory(category);
    }

}
