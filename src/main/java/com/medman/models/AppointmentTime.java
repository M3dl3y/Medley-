package com.medman.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment_times")
public class AppointmentTime {

    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private LocalDateTime appointmentDate;

    @Column
    private String notes;

    @Column
    private Long userId;

}
