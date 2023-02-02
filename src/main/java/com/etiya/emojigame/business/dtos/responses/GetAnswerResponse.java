package com.etiya.emojigame.business.dtos.responses;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetAnswerResponse {
    private int numberOfCorrectAnswer;
    private int point;
    private int userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
