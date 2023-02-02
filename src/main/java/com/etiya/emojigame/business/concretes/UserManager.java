package com.etiya.emojigame.business.concretes;

import com.etiya.emojigame.business.abstracts.UserService;
import com.etiya.emojigame.business.constants.Messages;
import com.etiya.emojigame.business.dtos.requests.AddUserRequest;
import com.etiya.emojigame.business.dtos.responses.AddUserResponse;
import com.etiya.emojigame.core.utils.exceptions.BusinessException;
import com.etiya.emojigame.core.utils.messages.MessageService;
import com.etiya.emojigame.core.utils.results.DataResult;
import com.etiya.emojigame.core.utils.results.Result;
import com.etiya.emojigame.core.utils.results.SuccessDataResult;
import com.etiya.emojigame.core.utils.results.SuccessResult;
import com.etiya.emojigame.entities.User;
import com.etiya.emojigame.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;


@Service
public class UserManager implements UserService {

    private UserRepository userRepository;
    private MessageService messageService;

    @Autowired
    public UserManager(UserRepository userRepository, MessageService messageService) { //DI
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    @Override
    @Transactional
    public DataResult<AddUserResponse> addUser(AddUserRequest addUserRequest) {

        User user = new User();

        checkUserExistWithSameName(addUserRequest.getUserName());

        user.setUserName(addUserRequest.getUserName());

        User savedUser = this.userRepository.save(user);

        AddUserResponse addUserResponse = new AddUserResponse();

        addUserResponse.setUserName(savedUser.getUserName());
        addUserResponse.setId(savedUser.getId());


        return new SuccessDataResult<AddUserResponse>(addUserResponse, Messages.User.userAdded);

    }

    private void checkUserExistWithSameName(String userName) {
        User user = this.userRepository.findByUserName(userName);

        if (user != null) {
            throw new BusinessException(Messages.User.userAlreadyExist);
        }

    }


}
