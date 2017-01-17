package com.medman.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "medications")
public class Medication {

    private Long Id;

    private String brandName;

    private String genericName;
}
