package com.etiya.emojigame.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAnswerResponse {
    private int numberOfCorrectAnswer;
    private int point;
    private int userId;
}
