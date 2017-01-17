package com.medman.models;

import javax.persistence.*;

@Entity
@Table(name = "notification_settings")
public class NotificationSetting {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @OneToOne(mappedBy = "notification_settings")
    private Long userId;

    @Column
    private Long textMessage;

    @Column
    private Long email;

    @Column
    private Long prescriptionId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(Long textMessage) {
        this.textMessage = textMessage;
    }

    public Long getEmail() {
        return email;
    }

    public void setEmail(Long email) {
        this.email = email;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }


}
