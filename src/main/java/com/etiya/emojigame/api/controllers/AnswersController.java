package com.etiya.emojigame.api.controllers;

import com.etiya.emojigame.business.abstracts.AnswerService;
import com.etiya.emojigame.business.constants.Paths;
import com.etiya.emojigame.business.dtos.requests.GetAnswerRequest;
import com.etiya.emojigame.core.utils.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Paths.apiPrefix + "answers/")
@CrossOrigin(origins = {"http://localhost:8082/","https://localhost:8082/","https://emojigame.etiyadim.com/"})
public class AnswersController {
    private AnswerService answerService;

    @Autowired
    public AnswersController(AnswerService answerService) { //DI
        this.answerService = answerService;
    }

    @PostMapping("get-answer")
    public Result getAnswer(@RequestBody GetAnswerRequest answerRequest) {
        return this.answerService.getAnswer(answerRequest);
    }
}