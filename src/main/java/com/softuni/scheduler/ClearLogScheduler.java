package com.softuni.scheduler;

import com.softuni.service.LogService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClearLogScheduler {

    private final LogService logService;

    public ClearLogScheduler(LogService logService) {
        this.logService = logService;
    }

    @Scheduled(cron = "0 0 0 * * 0")
    private void clearLog(){
        this.logService.clearLog();
    }


}
