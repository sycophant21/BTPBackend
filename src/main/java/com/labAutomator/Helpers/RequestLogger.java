package com.labAutomator.Helpers;

import com.labAutomator.Repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Access;

public class RequestLogger {
    private LogRepository repository;
    private static RequestLogger requestLogger;
    private final AsyncLogAppender asyncLogAppender;
    private RequestLogger() {
        asyncLogAppender = new AsyncLogAppender();
    }

    public static RequestLogger getInstance() {
        return requestLogger;
    }
    public void logRequest(String abc) {
        asyncLogAppender.write(() -> repository.save(new Log()));
    }
    static {
        requestLogger = new RequestLogger();
    }

    public void setLogRepository(LogRepository logRepository) {
        this.repository = logRepository;
    }
}
