package com.medman.models;

import javax.persistence.*;

/**
 * Created by jessedavila on 1/24/17.
 */
@Entity
@Table(name = "doctor_patient_relationships")
public class DoctorPatientRelationship {

    public DoctorPatientRelationship() {}

    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private Long Doctor;

    @Column
    private Long Patient;

    public Long getDoctor() {
        return Doctor;
    }

    public void setDoctor(Long doctor) {
        Doctor = doctor;
    }

    public Long getPatient() {
        return Patient;
    }

    public void setPatient(Long patient) {
        Patient = patient;
    }
}
