package com.example.demo.dto;


public class UserDTO {
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String picture="";
    private String phone;
    private Float rating;

    public UserDTO() {

    }

    public UserDTO(Long userId, String email, String firstName, String lastName, String picture, String phone, Float rating) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.phone = phone;
        this.rating = rating;
    }

    public UserDTO(Long id, String firstName, String lastName, String picture) {
        this.userId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture ="";
    }

    public UserDTO(Long id, String firstName, String lastName, Float rating) {
        this.userId =id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
