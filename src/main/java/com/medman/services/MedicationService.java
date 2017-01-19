package com.medman.services;

import com.medman.controllers.ForbiddenException;
import com.medman.models.Medication;
import com.medman.models.Prescription;



public interface MedicationService {

    Medication getMedication(Long id);

    Long saveNewMedication(Medication medication, Prescription prescription, Long parentId);

    void deleteMedication(Long medicationId) throws ActionExpiredException;

    void updateMedication(Medication newMedicationData, Long medicationId) throws ActionExpiredException;



}
