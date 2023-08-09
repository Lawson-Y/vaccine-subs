package com.lawson.vaccine.subscribe.config;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class TriggerImpl implements Trigger {

    private LocalDateTime dateTime;

    public TriggerImpl(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        return Date.from(this.dateTime.toInstant(ZoneOffset.ofHours(8)));
    }
}
