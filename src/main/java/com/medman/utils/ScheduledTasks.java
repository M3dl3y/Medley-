package com.medman.utils;

import com.medman.models.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class ScheduledTasks {

    @Autowired
    TwillioService twillioService;

    @Autowired
    Appointments appointments;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    private static final AppointmentReminder appointment = AppointmentReminder.getAppointment()

//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//    }

//    @Scheduled(cron="0 0 8 * * MON-FRI")
    @Async
    public void appReminder() {
        List<AppointmentTime> apps = appointments.findByAppointmentDate(new Date());
        for (AppointmentTime app : apps) {
            System.out.println(app);
            if (app.getUser() != null) {
                System.out.println("You have scheduled an appointment for today");
//                twillioService.sendSMS("You have an appointment to day", (app.getUser().getPhoneNumber()));
            }
        }
    }
}