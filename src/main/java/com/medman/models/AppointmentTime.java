package com.medman.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment_times")
public class AppointmentTime {

    private Long Id;

    private LocalDateTime appointmentDate;

    private String notes;

    private Long userId;

}
