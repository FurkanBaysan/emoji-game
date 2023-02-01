package com.etiya.emojigame.business.dtos.responses;

import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllGameResultResponse {
    private int point;

   // TODO: should be retrieved from UI.
    //private int totalTime;

    private String userName;
}
