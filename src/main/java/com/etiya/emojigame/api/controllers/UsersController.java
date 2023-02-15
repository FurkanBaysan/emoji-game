package com.etiya.emojigame.api.controllers;

import com.etiya.emojigame.business.abstracts.UserService;
import com.etiya.emojigame.business.constants.Paths;
import com.etiya.emojigame.business.dtos.requests.AddUserRequest;
import com.etiya.emojigame.business.dtos.responses.AddUserResponse;
import com.etiya.emojigame.core.utils.results.DataResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Paths.apiPrefix + "users/")
@CrossOrigin(origins = "http://localhost:8080/")
public class UsersController {
    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("add-user")
    public ResponseEntity<DataResult<AddUserResponse>> addUser(@RequestBody @Valid AddUserRequest addUserRequest) {
        DataResult<AddUserResponse> addUserResponse = this.userService.addUser(addUserRequest);
        return new ResponseEntity<DataResult<AddUserResponse>>(addUserResponse, HttpStatus.CREATED);
    }


}
