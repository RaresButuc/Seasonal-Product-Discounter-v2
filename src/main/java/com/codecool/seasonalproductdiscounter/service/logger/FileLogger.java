package com.codecool.seasonalproductdiscounter.service.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class FileLogger extends BaseLogger {
    private final String logFile;

    public FileLogger(String logFile) {
        this.logFile = logFile;
    }
}
