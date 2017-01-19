package com.medman.services;

import com.medman.models.Prescription;

public class PrescriptionServiceImpl implements PrescriptionService{


    @Override
    public Prescription getPrescription(Long id) {

        Prescription prescription = getPrescription(id);

        if (prescription == null)
            return null;

        return Prescription;
    }

    @Override
    public Prescription saveNewPrescription() {
        return null;
    }

    @Override
    public Prescription updatePrescription(String newPrescription) {
        return null;
    }

    @Override
    public void deletePrescription(Long prescriptionId) {

    }
}
