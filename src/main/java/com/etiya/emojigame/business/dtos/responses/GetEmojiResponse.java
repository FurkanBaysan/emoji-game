package com.etiya.emojigame.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetEmojiResponse {
    private String category;
    private int id;
    private String imageUrl;
    private int questionId;
}
