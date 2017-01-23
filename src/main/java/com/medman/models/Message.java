package com.medman.models;


import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    private User user;

    @Column (nullable = false)
    private String messageContent;

//    @Column
//    private Long relationship;

    public Message() {}

    public Message (Message message) {
        Id = message.Id;
        messageContent = message.messageContent;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    //    public long getRelationship() {
//        return relationship;
//    }
//
//    public void setRelationship(long relationship) {
//        this.relationship = relationship;
//    }
}
