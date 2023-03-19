package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @Column(name="answer_id")
    private Long answerId;

    @Column(name="author", insertable=false, updatable=false)
    private Long authorId;


    @Column(name="text_answer")
    private String text;

    @Column(name="creation")
    private Date creationTime;

    @Column(name="picture")
    private String picture;


    @Column(name="question_id", insertable=false, updatable=false)
    private Long questionId;



    public Answer() {

    }

    public Answer(Long answerId, Long authorId, String text, Date creationTime, String picture, Long questionId) {
        this.answerId = answerId;
        this.authorId = authorId;
        this.text = text;
        this.creationTime = creationTime;
        this.picture = picture;
        this.questionId = questionId;
    }



    public Answer(Long answerId, Long authorId, String text, Date creationTime, Long questionId) {
        this.answerId = answerId;
        this.authorId = authorId;
        this.text = text;
        this.creationTime = creationTime;
        this.questionId = questionId;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="question_id", nullable=false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Question question;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author", nullable=false)
   // @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

}
