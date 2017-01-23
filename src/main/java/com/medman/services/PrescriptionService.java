//package com.medman.services;
//
//import com.medman.models.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//public interface PrescriptionService {
//
//    List<Prescription> getPrescriptionList(int prescriptionId, int numberOfPrescriptions);
//
//    Prescription getPrescription(Long id);
//
//    Prescription saveNewPrescription(Prescription prescription);
//
//    Prescription updatePrescription(Prescription prescription);
//
//    void deletePrescription(Long prescriptionId);
//
//}
