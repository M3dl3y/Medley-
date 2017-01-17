package com.medman.models;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

    private Long id;

    private String message;

    private Long relationship;
}
