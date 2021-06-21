package com.labAutomator.Helpers;

import com.labAutomator.Repositories.LogRepository;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class Config {

    @Autowired
    private LogRepository logRepository;

    @PostConstruct
    public void init() {
        RequestLogger.getInstance().setLogRepository(logRepository);
    }


}
