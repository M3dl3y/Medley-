package com.medman.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Nick on 1/21/17.
 */
public interface Medications extends CrudRepository<Medication, Long> {
//    @Query("SELECT m.brandName,m.genericName FROM Medication m, Prescription p,  ")
}
