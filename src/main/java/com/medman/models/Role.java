package com.medman.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {

    private Long id;

    private String role;

    private Collection<User> user_id = new ArrayList<>();

}
