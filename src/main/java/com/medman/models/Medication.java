package com.medman.models;

import javax.persistence.*;

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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
