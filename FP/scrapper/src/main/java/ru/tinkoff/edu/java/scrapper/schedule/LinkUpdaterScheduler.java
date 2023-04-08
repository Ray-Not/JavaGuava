package ru.tinkoff.edu.java.scrapper.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LinkUpdaterScheduler {
    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    public void update() {
        LOGGER.log(Level.INFO, "Info scrapper called");
    }
}
