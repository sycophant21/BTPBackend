package com.labAutomator.Helpers;

import com.labAutomator.Repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Logger {
    @Autowired
    private LogRepository logRepository;

    @Async
    public void logRequest(String ipAddress, String requestType, String requestURL, String[] requestDetails) {
        String detailsToSave = "\"";
        for (String string : requestDetails) {
            detailsToSave = detailsToSave.concat(string + "\n");
        }
        detailsToSave = detailsToSave.substring(0, detailsToSave.length() - 1).concat("\"");
        logRepository.save(new Log(ipAddress, requestType, requestURL, detailsToSave));
    }


}
