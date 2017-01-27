package com.medman.utils;

import com.medman.models.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class ScheduledTasks {

    @Autowired
    TwillioService twillioService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    private static final AppointmentReminder appointment = AppointmentReminder.getAppointment()

//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//    }

//    @Scheduled(cron="*/5 * * * * MON-FRI")
//    public void appReminder(@Valid User user) {
//
//        if(user.hasAppointmentToday){
//            twillioService.sendSMS("You have an appointment to day", (user.getPhoneNumber()));
//        }

}