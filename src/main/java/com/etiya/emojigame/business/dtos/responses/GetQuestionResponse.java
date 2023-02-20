package com.etiya.emojigame.business.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetQuestionResponse {
    private int id;
    private String category;
    private List<GetEmojiResponse> getEmojiResponses;
}
