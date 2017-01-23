//package com.medman.models;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import java.util.Collection;
//import java.util.List;
//
//import static javafx.scene.input.KeyCode.T;
//
//
//@Service("customPrescriptionDetailsService")
//public class PrescriptionDetailsLoader implements PrescriptionDetailService {
//    private final PrescriptionDetailService prescriptionDetailService;
//    private List<String> prescriptionMedication;
//
//    @Autowired
//    public PrescriptionDetailsLoader (PrescriptionDetailService prescriptionDetailService, Medication medication) {
//        this.prescriptionDetailService = prescriptionDetailService;
//        this.prescriptionMedication = prescriptionMedication;
//
//    }
//
//
//    @Override
//    public Prescription findOne(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public boolean exists(Long aLong) {
//        return false;
//    }
//
//    @Override
//    public Iterable<Prescription> findAll() {
//        return null;
//    }
//
//    @Override
//    public Iterable<Prescription> findAll(Iterable<Long> iterable) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void delete(Long aLong) {
//
//    }
//
//    @Override
//    public void delete(Prescription prescription) {
//
//    }
//
//    @Override
//    public void delete(Iterable<? extends Prescription> iterable) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//}
