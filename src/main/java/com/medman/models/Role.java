package com.medman.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> user_id = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUser_id() {
        return user_id;
    }

    public void setUser_id(Collection<User> user_id) {
        this.user_id = user_id;
    }


}
