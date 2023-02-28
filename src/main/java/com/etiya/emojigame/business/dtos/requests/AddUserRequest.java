package com.etiya.emojigame.business.dtos.requests;


import com.etiya.emojigame.business.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddUserRequest {
    @Pattern(regexp = "(^[a-zA-Z0-9\\W][a-zA-Z][a-zA-Z0-9\\W+]+$)", message = Messages.User.userNameMustContainLetters)
    @Size(min = 3, max = 15)
    private String userName;
}
