package com.medman.repositories;

import com.medman.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    Medication findByBrandNameIgnoreCase(String brandName);

    Medication findByGenericOrBrandName(String genericName);

}
