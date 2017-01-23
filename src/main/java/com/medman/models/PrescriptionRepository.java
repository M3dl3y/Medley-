package com.medman.repositories;

import com.medman.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
    @Query("select p from Prescription p, User u where u.id=?1 and u.id = p.user.id")
    public List<Prescription> findByPatient(Long id);
}
