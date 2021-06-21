package com.labAutomator.Helpers;

import com.labAutomator.Repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LoggerTask implements Runnable{
    @Autowired
    private LogRepository repository;
    @Override
    public void run() {

    }
}
