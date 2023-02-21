package com.etiya.emojigame.business.dtos.requests;


import com.etiya.emojigame.business.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddUserRequest {
    @Pattern(regexp = "(^[a-zA-Z0-9\\W][a-zA-Z][a-zA-Z0-9\\W+]+$)", message = Messages.User.userNameMustContainLetters)
    private String userName;
}
