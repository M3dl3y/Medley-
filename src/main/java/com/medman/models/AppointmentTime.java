package com.medman.models;

import com.sun.istack.internal.Nullable;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "appointment_times")
public class AppointmentTime {

    @Id
    @GeneratedValue
    private Long Id;

    @Column
    @NotBlank(message = "Please name this appointment")
    private String name;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;

    @Column
    @DateTimeFormat(pattern = "HH:mm")
    private Time appointmentTimes;

    @Column
    private String notes;

    @ManyToOne
    private User user;

    public AppointmentTime(){

    }

    public AppointmentTime(long Id, String name, Date appointmentDate, Time appointmentTimes, String notes){
        this.Id = Id;
        this.name = name;
        this.appointmentDate = appointmentDate;
        this.appointmentTimes = appointmentTimes;
        this.notes = notes;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Time getAppointmentTimes() {
        return appointmentTimes;
    }

    public void setAppointmentTimes(Time appointmentTimes) {
        this.appointmentTimes = appointmentTimes;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
