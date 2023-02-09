package com.etiya.emojigame.api.controllers;

import com.etiya.emojigame.business.abstracts.ScoreService;
import com.etiya.emojigame.business.constants.Paths;
import com.etiya.emojigame.business.dtos.responses.GetAllGameResultResponse;
import com.etiya.emojigame.core.utils.results.DataResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "Scores/")
@CrossOrigin(origins = "*")
public class ScoresController {
    private ScoreService scoreService;

    public ScoresController(ScoreService scoreService) { //DI
        this.scoreService = scoreService;
    }

    // End-Point for => listing users according to their points situation
    @GetMapping("getAllGameResult")
    public DataResult<List<GetAllGameResultResponse>> getAllGameResult() {
        return this.scoreService.getAllGameResult();
    }
}
