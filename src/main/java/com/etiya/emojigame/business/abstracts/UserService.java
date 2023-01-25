package com.etiya.emojigame.business.abstracts;

import com.etiya.emojigame.business.dtos.requests.AddUserRequest;
import com.etiya.emojigame.business.dtos.responses.AddUserResponse;
import com.etiya.emojigame.core.utils.results.DataResult;

public interface UserService {
    DataResult<AddUserResponse> addUser(AddUserRequest addUserRequest);


}
