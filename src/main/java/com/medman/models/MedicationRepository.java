package com.medman.models;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicationRepository extends CrudRepository<Medication, Long> {
}
