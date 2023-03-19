package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * create table tags (
 * tag varchar(20) not null,
 * question_id int not null,
 * foreign key (question_id) references question(question_id)
 * );
 *
 * */

@Entity
@Table(name = "tags")
public class Tags {

    @Id
    @Column(name="tag_id")
    private Long tagId;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name="question_id")
    private Long questionId;

    public Tags() {
    }

    public Tags(Long tagId, String tagName, Long questionId) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.questionId = questionId;
    }


    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
