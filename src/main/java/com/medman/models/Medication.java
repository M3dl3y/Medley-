package com.medman.models;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "medication")
    private List<Prescription> prescriptions;
}
