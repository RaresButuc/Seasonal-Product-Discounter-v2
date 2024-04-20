package com.codecool.seasonalproductdiscounter.service.logger;

import java.time.LocalDateTime;

public abstract class BaseLogger implements Logger {
    private static String createLogEntry(String message, String type) {
        return String.format("[%s] %s: %s", LocalDateTime.now(), type, message);
    }

    @Override
    public void logInfo(String message) {
        System.out.println(createLogEntry(message, "INFO"));
    }

    @Override
    public void logError(String message) {
        System.out.println(createLogEntry(message, "ERROR"));
    }
}
