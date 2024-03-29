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
    private double second;
    private int point;
    private String userName;

}
