package com.labAutomator.Handlers;

import com.labAutomator.Helpers.RequestLogger;
import com.labAutomator.Repositories.UserRepository;
import com.labAutomator.domain.User;
import com.labAutomator.domain.id.UserID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserHandler {

    private RequestLogger requestLogger = RequestLogger.getInstance();
    private Logger logger = LoggerFactory.getLogger(UserHandler.class);

    @Autowired
    private UserRepository userRepository;

    public void insertUser(User user) {

        userRepository.save(user);
        logger.info(String.format("User Inserted %s", user.getUserID().getUserID()));
    }

    public User getUser(UserID userID) {
        return userRepository.findById(userID).orElse(null);
    }

    public User getUser(String emailId) {
        return userRepository.getUserByEmailID(emailId);
    }

    public boolean checkExistence(String emailId) {
        logger.info(String.format("Checking User Existence with emailID = %s", emailId));
        return userRepository.getUserByEmailID(emailId) != null;
    }
}
