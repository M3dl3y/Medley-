package com.medman.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jessedavila on 1/24/17.
 */
@Repository
public interface DoctorPatients extends CrudRepository<DoctorPatientRelationship, Long> {

//    @Query("select dpr.Doctor from DoctorPatientRelationship dpr, User u where dpr.Patient = ?1")
//    List<User> findDoctorsByPatient(Long id);

//    @Query("select dpr.Doctor from DoctorPatientRelationship dpr, User u where dpr.Patient = ?1")
//    List<User> findDoctorsByPatient(Long id);

    @Query("select dpr.Patient from DoctorPatientRelationship dpr where dpr.Doctor = ?1")
    List<Long> findByDoctor(Long id);


}
