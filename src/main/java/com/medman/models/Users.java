package com.medman.models;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nick on 1/19/17.
 */
public interface Users extends CrudRepository<User, Long> {
    public User findByUsername(String username);

}
