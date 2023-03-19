package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @Column(name="answer_id")
    private Long answerId;

    @Column(name="author")
    private Long authorId;


    @Column(name="text_answer")
    private String text;

    @Column(name="creation")
    private Date creationTime;

    @Column(name="picture")
    private String picture;


    public Answer() {

    }

    public Answer(Long answerId, Long authorId, String text, Date creationTime, String picture) {
        this.answerId = answerId;
        this.authorId = authorId;
        this.text = text;
        this.creationTime = creationTime;
        this.picture = picture;
    }



    public Answer(Long answerId, Long authorId, String text, Date creationTime) {
        this.answerId = answerId;
        this.authorId = authorId;
        this.text = text;
        this.creationTime = creationTime;

    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
