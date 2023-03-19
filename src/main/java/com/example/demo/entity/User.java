package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")

public class User {

    @Id
    @Column(name="user_id")
    private Long userId;

    @Column(name = "l_name")
    private String lastName;

    @Column(name = "f_name")
    private String firstName;

    @Column(name = "e_mail")
    private String email;



    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<Answer> answerSet;


    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<Question> questionSet;




    public User() {

    }

    public User(Long userId, String lastName, String firstName, String email) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
