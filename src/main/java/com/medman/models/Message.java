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

    @Column
    private Long relationship;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getRelationship() {
        return relationship;
    }

    public void setRelationship(Long relationship) {
        this.relationship = relationship;
    }
}
