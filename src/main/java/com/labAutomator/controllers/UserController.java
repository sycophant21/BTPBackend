package com.labAutomator.controllers;

import com.labAutomator.Handlers.UserHandler;
import com.labAutomator.domain.User;
import com.labAutomator.domain.id.UserID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserHandler userHandler;

    @PutMapping("/insertUser")
    public void insertUser(@RequestParam("name") String name, @RequestParam("emailID")String emailID) {
        logger.info(String.format("Performing request on url \"/insertUser\" with parameters name = %s and emailAddress = %s", name, emailID));
        User user = new User(name, emailID);
        if (getUserWithEmailID(emailID) == null) {
            userHandler.insertUser(user);
        }
    }

    @GetMapping("/getUserWithUserID")
    public User getUserWithUserID(@RequestParam("userID")String userID) {
        logger.info(String.format("Performing request on url \"/getUserWithUserID\" with parameters userID = %s", userID));
        return userHandler.getUser(new UserID(userID));
    }

    @GetMapping("/getUserWithEmailID")
    public User getUserWithEmailID(@RequestParam("emailID")String emailID) {
        logger.info(String.format("Performing request on url \"/getUserWithEmailID\" with parameters emailAddress = %s", emailID));
        return userHandler.getUser(emailID);
    }

    @GetMapping("/checkUserExistence")
    public boolean checkUserExistence(@RequestParam("emailID")String emailID) {
        logger.info(String.format("Performing request on url \"/checkUserExistence\" with parameters emailAddress = %s",emailID));
        return userHandler.checkExistence(emailID);
    }

}
