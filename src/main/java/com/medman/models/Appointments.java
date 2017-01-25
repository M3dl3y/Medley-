package com.medman.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Nick on 1/20/17.
 */
public interface Appointments extends CrudRepository<AppointmentTime, Long> {

    @Query("select at from AppointmentTime at, User u where u.id=?1 and u.id = at.user.id")
    public List<AppointmentTime> findByUserId(Long id);
}
