package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="answer_id")
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="question_id")
    private Question questionId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @Column(name="text_answer")
    private String text;

    @Column(name="creation")
    private Timestamp timeStamp;

    @Column(name="updated")
    private Timestamp updated;

    @Column(name="picture")
    private String image;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Column (name="vote")
    private Integer rating;

    public Answer() {

    }
    public Answer(Integer id, Question questionId, User author, String text, Timestamp timeStamp, String image) {
        this.id = id;
        this.questionId = questionId;
        this.author = author;
        this.text = text;
        this.timeStamp = timeStamp;
        this.image = image;
    }

    public Answer(Integer id, Question questionId, User author, String text, Timestamp timeStamp, Timestamp updated, String image) {
        this.id = id;
        this.questionId = questionId;
        this.author = author;
        this.text = text;
        this.timeStamp = timeStamp;
        this.updated = updated;
        this.image = image;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
