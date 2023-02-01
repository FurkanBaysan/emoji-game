package com.etiya.emojigame.business.dtos.responses;

import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllGameResultResponse {
    private int point;

    // TODO: should be retrieved from UI.

    private String userName;

    private Date totalTime;


}
