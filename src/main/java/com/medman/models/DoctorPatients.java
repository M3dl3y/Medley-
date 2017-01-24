package com.medman.models;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by jessedavila on 1/24/17.
 */
public interface DoctorPatients extends CrudRepository<DoctorPatientRelationship, Long> {

}
