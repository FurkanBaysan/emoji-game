package com.etiya.emojigame.business.concretes;

import com.etiya.emojigame.EmojigameApplication;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.regex.Pattern;


@Service
@EnableScheduling
public class UserManager implements UserService {

    private UserRepository userRepository;
    private MessageService messageService;

    Logger logger = LoggerFactory.getLogger(EmojigameApplication.class);

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

    public void saveUser(User user) {
        this.userRepository.save(user);
    }


    @Override
    @Scheduled(cron = "0 39 16 ? * *")
    public void job() {

        this.logger.info("Log Current Time: " + new Date());

        User user = new User();
        user.setUserName("UserThatAddedWithCronJob");

        UserManager userManager = new UserManager(userRepository, messageService);

        //userManager.saveUser(user);
        //saveUser(user);

        //this.userRepository.deleteAllUserRecords();
        this.userRepository.deleteAll();

    }


}
