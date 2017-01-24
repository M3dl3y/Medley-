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

}
