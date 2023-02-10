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
public class AddEmojiRequest {
    //private int questionId;
    private List<String> imageUrls;
    private String answer;
}
