package com.medman.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "notification_settings")
public class NotificationSetting {

    private Long id;

    private Long userId;

    private Long textMessage;

    private Long email;

    private Long prescriptionId;
}
