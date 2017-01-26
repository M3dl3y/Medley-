package com.medman.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Nick on 1/20/17.
 */
public interface Appointments extends CrudRepository<AppointmentTime, Long> {
    @Query("select p from AppointmentTime p, User u where u.id=?1 and u.id = p.user.id")
    public List<AppointmentTime> findByPatient(Long id);
    public List<AppointmentTime> findByUserAndAppointmentDateBetween(User user, Date from, Date to);
}
