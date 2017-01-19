package com.medman.services;

import com.medman.models.Prescription;

import java.util.List;

public interface PrescriptionService {

    List<Prescription> listAllPrescription();

    Prescription getPrescription(Long id);

    Prescription saveNewPrescription();

    Prescription updatePrescription();

    void deletPrescription(Long prescriptionId);
}
