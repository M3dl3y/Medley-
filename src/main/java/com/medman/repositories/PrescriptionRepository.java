package com.medman.repositories;

import com.medman.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {

    @Query("select p from Prescription p, User u where u.id=?1 and u.id = p.user.id")
    public List<Prescription> findByPatient(Long id);

    @Query("select p from Prescription p, User u where p.daySupply <= 3 and u.Id =?1")
    public List<Prescription> findByDaySupplyAlert(Long id);

//    @Query("select p from Prescription p, User u where p.daySupply <= 3 and p.user.id =?1")
//    public List<Prescription> findByUser(Long id);
}
