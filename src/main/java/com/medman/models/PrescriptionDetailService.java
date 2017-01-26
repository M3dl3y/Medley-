package com.medman.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrescriptionDetailService extends CrudRepository<Prescription, Long> {
    @Query("select p from Prescription p, User u where p.daySupply <= 3 and p.user.id =?1")
    public List<Prescription> findByDaySupplyAlert(Long id);
}
