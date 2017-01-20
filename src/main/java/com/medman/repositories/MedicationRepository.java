package com.medman.repositories;

import com.medman.models.Medication;
import org.springframework.data.repository.CrudRepository;

public interface MedicationRepository extends CrudRepository<Medication, Long> {

//    Medication findByBrandName(String brandName);
//
//    Medication findByGenericName(String GerericName);
}
