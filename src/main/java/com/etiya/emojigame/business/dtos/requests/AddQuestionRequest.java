package com.etiya.emojigame.business.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddQuestionRequest {
    private String category;
    private String answer;
    private List<String> imageUrls;
}
