package com.etiya.emojigame.business.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddUserRequest {

    //@Pattern(regexp="(^[0-9]{11}$)", message = "Nationality id must consist of digits and 11 characters.")
    @Pattern(regexp = "(^[a-zA-Z0-9][a-zA-Z0-9 ]+$)", message = "username must be contain letters.")
    private String userName;
}
