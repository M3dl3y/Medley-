package com.medman.services;

import com.medman.models.Prescription;

import java.util.List;

public interface PrescriptionService {

    Prescription getPrescription(Long id);

    Prescription saveNewPrescription();

    Prescription updatePrescription(String newPrescription);

    void deletePrescription(Long prescriptionId);
}
