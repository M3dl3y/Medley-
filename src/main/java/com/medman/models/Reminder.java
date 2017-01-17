package com.medman.models;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "reminders")
public class Reminder {

    private Long Id;

    private String reminder;

    private LocalDateTime reminderDate;

    private Long userId;
}
