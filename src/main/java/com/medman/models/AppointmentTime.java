package com.medman.models;

import com.sun.istack.internal.Nullable;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private String title;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date appDate;

    @Column
    private String notes;

    @ManyToOne
    private User user;

    public AppointmentTime(){

    }

    public AppointmentTime(long Id, String title, Date appDate, String notes){
        this.Id = Id;
        this.title = title;
        this.appDate = appDate;
        this.notes = notes;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
