package com.medman.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Users extends CrudRepository<User, Long> {
    public User findByUsername(String username);
//
    @Query("select u.id from User u where u.generated_identifier = ?")
    public Long findByDocNum(Long id);

}
