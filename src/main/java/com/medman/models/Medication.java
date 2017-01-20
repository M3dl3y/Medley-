package com.medman.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medications")
public class Medication {

    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private String brandName;

    @Column
    private String genericName;


//    @OneToMany(mappedBy = "medication")
//    private List<Prescription> prescriptions;

    public Medication(){}

    public Medication(Medication medication){
        Id = medication.Id;
        brandName = medication.brandName;
        genericName = medication.genericName;
//        prescriptions = new ArrayList<>();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

//    public List<Prescription> getPrescriptions() {
//        return prescriptions;
//    }
//
//    public void setPrescriptions(List<Prescription> prescriptions) {
//        this.prescriptions = prescriptions;
//    }

}
