package com.medman.services;

import com.medman.models.*;
import com.medman.repositories.MedicationRepository;
import com.medman.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("prescrptionService")
public class PrescriptionServiceImpl implements PrescriptionService{

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private Users users;

    @Override
    public List<Prescription> getPrescriptionList(int prescriptionId, int numberOfPrescriptions) {
        return null;
    }

    @Override
    public Prescription getPrescription(Long id) {
        return null;
    }

    @Override
    public Prescription saveNewPrescription(Prescription prescription) {
        return null;
    }

    @Override
    public Prescription updatePrescription(Prescription prescription) {
        return null;
    }

    @Override
    public void deletePrescription(Long prescriptionId) {

    }
}
