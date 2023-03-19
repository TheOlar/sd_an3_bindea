package com.example.demo.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @Column(name="question_id")
    private Long questionId;

    @Column(name="author", insertable=false, updatable=false)
    private Long authorId;

    @Column(name="title")
    private String title;

    @Column(name="text_question")
    private String text;

    @Column(name="creation")
    private Date creationTime;

    @Column(name="picture")
    private String picture;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author", nullable=false)
   // @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(mappedBy="question", cascade = CascadeType.ALL)
    private Set<Answer> answerSet;

    public Question() {

    }

    public Question(Long questionId, Long authorId, String title, String text, Date creationTime, String picture) {
        this.questionId = questionId;
        this.authorId = authorId;
        this.title = title;
        this.text = text;
        this.creationTime = creationTime;
        this.picture = picture;
    }

    public Question(Long questionId, Long authorId, String text, Date creationTime, String picture) {
        this.questionId = questionId;
        this.authorId = authorId;
        this.text = text;
        this.creationTime = creationTime;
        this.picture = picture;

    }

    public Question(Long questionId, Long authorId, String text, Date creationTime) {
        this.questionId = questionId;
        this.authorId = authorId;
        this.text = text;
        this.creationTime = creationTime;

    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
