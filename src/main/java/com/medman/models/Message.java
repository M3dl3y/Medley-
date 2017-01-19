package com.medman.models;


import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private String message;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

//    @OneToOne(mappedBy = "messages")
//    private Long relationship;
}
