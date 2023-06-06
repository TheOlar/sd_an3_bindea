package com.example.demo.dto;

import java.sql.Timestamp;

public class ContentDTO {

    private Integer id;
    private String text;
    private Timestamp creation;
    private UserDTO author;
    private String picture = "";
    private Integer vote;

    public ContentDTO() {}
    public ContentDTO(Integer id, String text, Timestamp creation, UserDTO author, String picture, Integer vote) {
        this.id =id;
        this.text = text;
        this.creation = creation;
        this.author = author;
        this.picture = picture;
        this.vote = vote;
    }

    public ContentDTO(Integer id, String text, Timestamp creation, UserDTO author) {
        this.id = id;
        this.text = text;
        this.creation = creation;
        this.author = author;
        this.vote = 0;
    }

    public ContentDTO(Integer id, String text, Timestamp creation, UserDTO author, Integer vote) {
        this.id = id;
        this.text = text;
        this.creation = creation;
        this.author = author;
        this.vote = vote;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreation() {
        return creation;
    }

    public void setCreation(Timestamp creation) {
        this.creation = creation;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
