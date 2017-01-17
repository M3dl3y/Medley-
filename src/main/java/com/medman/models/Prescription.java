package com.medman.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    private Long Id;

    private LocalDateTime prescribedDate;

    private Long medicationId;

    private String strength;

    private String sig;

    private Long dosageAmount;

    private String dosageForm;

    private String dosageRoute;

    private Long dosageFrequency;

    private String dosageInterval;

    private Long daySupply;

    private Long prescribedQuantity;

    private Long userId;


}
