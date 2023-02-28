package com.etiya.emojigame.api.controllers;

import com.etiya.emojigame.business.abstracts.ScoreService;
import com.etiya.emojigame.business.constants.Paths;
import com.etiya.emojigame.business.dtos.responses.GetAllGameResultResponse;
import com.etiya.emojigame.core.utils.results.DataResult;
import com.etiya.emojigame.core.utils.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "scores/")
@CrossOrigin(origins = {"http://localhost:8082/", "https://localhost:8082/", "https://emojigame.etiyadim.com/"})
public class ScoresController {
    private ScoreService scoreService;

    public ScoresController(ScoreService scoreService) { //DI
        this.scoreService = scoreService;
    }

    // End-Point for => listing users according to their points situation
    @GetMapping("get-all-game-result")
    public DataResult<List<GetAllGameResultResponse>> getAllGameResult() {
        return this.scoreService.getAllGameResult();
    }

    /*@PostMapping("reset-related-user-score")
    public Result resetRelatedUserScore(@RequestParam int userId) {
        return this.scoreService.resetRelatedUserScore(userId);
    }*/
}