package com.medman.models;

import com.medman.utils.LocalDateTimePersistenceConverter;
import org.springframework.security.access.method.P;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(nullable = false)
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime prescribedDate;

    @ManyToOne
    private Medication medication;

    @Column(nullable = false)
    private String strength;

    @Column(nullable = false)
    private String sig;

    @Column(nullable = false)
    private Long dosageAmount;

    @Column(nullable = false)
    private String dosageForm;

    @Column(nullable = false)
    private String dosageRoute;

    @Column(nullable = false)
    private Long dosageFrequency;

    @Column(nullable = false)
    private String dosageInterval;

    @Column(nullable = false)
    private Long daySupply;

    @Column(nullable = false)
    private Long prescribedQuantity;

    public Prescription (){}

    public Prescription (Prescription prescription){
        Id = prescription.Id;
        prescribedDate = prescription.prescribedDate;
        strength = prescription.strength;
        sig = prescription.sig;
        dosageAmount = prescription.dosageAmount;
        dosageForm = prescription.dosageForm;
        dosageRoute = prescription.dosageRoute;
        dosageFrequency = prescription.dosageFrequency;
        dosageInterval = prescription.dosageInterval;
        daySupply = prescription.daySupply;
        prescribedQuantity = prescription.prescribedQuantity;

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDateTime getPrescribedDate() {
        return prescribedDate;
    }

    public void setPrescribedDate(LocalDateTime prescribedDate) {
        this.prescribedDate = prescribedDate;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public Long getDosageAmount() {
        return dosageAmount;
    }

    public void setDosageAmount(Long dosageAmount) {
        this.dosageAmount = dosageAmount;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getDosageRoute() {
        return dosageRoute;
    }

    public void setDosageRoute(String dosageRoute) {
        this.dosageRoute = dosageRoute;
    }

    public Long getDosageFrequency() {
        return dosageFrequency;
    }

    public void setDosageFrequency(Long dosageFrequency) {
        this.dosageFrequency = dosageFrequency;
    }

    public String getDosageInterval() {
        return dosageInterval;
    }

    public void setDosageInterval(String dosageInterval) {
        this.dosageInterval = dosageInterval;
    }

    public Long getDaySupply() {
        return daySupply;
    }

    public void setDaySupply(Long daySupply) {
        this.daySupply = daySupply;
    }

    public Long getPrescribedQuantity() {
        return prescribedQuantity;
    }

    public void setPrescribedQuantity(Long prescribedQuantity) {
        this.prescribedQuantity = prescribedQuantity;
    }
}