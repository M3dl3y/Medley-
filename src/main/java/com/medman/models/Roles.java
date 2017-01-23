package com.medman.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Roles extends CrudRepository<Role, Long> {
    //Select the user role where username
    @Query("select ur.role from Role ur, User u where u.username=?1 and ur.id = u.role.id")
    public List<String> ofUserWith(String userName);
    public Role findById(int i);
}
