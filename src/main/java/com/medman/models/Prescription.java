package com.medman.models;

import com.medman.utils.LocalDateTimePersistenceConverter;
import org.hibernate.Session;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date prescribedDate;

    @Column(nullable = false)
    private String strength;

    @Column(nullable = false)
    private String sig;

    @Column(nullable = true)
    private Long dosageAmount = 0L;

    @Column(nullable = true)
    private String dosageForm;

    @Column(nullable = true)
    private String dosageRoute;

    @Column(nullable = true)
    private Long dosageFrequency = 0L;

    @Column(nullable = true)
    private String dosageInterval;

    @Column(nullable = true)
    private Long daySupply = 0L;

    @Column(nullable = false)
    private Long prescribedQuantity = 0L;

    @Column(nullable = true)
    private Long pillsTaken = 0L;

    public Long getPillsTaken() {
        return pillsTaken;
    }

    public void setPillsTaken(Long pillsTaken) {
        this.pillsTaken = pillsTaken;
    }

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medication_id")
    private Medication medication;

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

    public Date getPrescribedDate() {
        return prescribedDate;
    }

    public void setPrescribedDate(Date prescribedDate) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    @Override
    public String toString() {
        return "day supply: " + daySupply +
                "id: " + getId() +
                " dosage amount " + dosageAmount +
                " dosage_form : " + dosageForm +
                " dosage_frequency: " + dosageFrequency +
                " dosage interval: " + dosageInterval +
                " dosage route: " + dosageRoute +
                " pills taken: " + pillsTaken +
                " prescribed date: " + prescribedDate +
                " prescribed quantity: " + prescribedQuantity +
                " sig: " + sig +
                " strength: " + strength;
    }


//    "select * from prescriptions where day_supply <= 3;";
}