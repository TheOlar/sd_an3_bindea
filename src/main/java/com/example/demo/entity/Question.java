package com.example.demo.entity;


import com.example.demo.dto.AnswerDTO;
import com.example.demo.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name="question")
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="author")
    private User author;

    @Column
    private String title;

    @Column(name="text_question")
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

    @Column
    private Integer rating;


    @JsonIgnore
    @OneToMany(mappedBy = "question", cascade=CascadeType.REMOVE)
    private List<VoteQuestion> votes = new ArrayList<>();


    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "question_tags",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tags> tags = new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "questionId",cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();




    public Question(Integer id, User author, String title, String text, Timestamp timeStamp, Timestamp updated, String image, List<Answer> answers) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
        this.timeStamp = timeStamp;
        this.updated = updated;
        this.image = image;
        this.answers = answers;
    }

    public Question(Integer id, User author, String title, String text, Timestamp timeStamp, Timestamp updated, String image, ArrayList<VoteQuestion> votes, List<Tags> tags, List<Answer> answers) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
        this.timeStamp = timeStamp;
        this.updated = updated;
        this.image = image;
        this.votes = votes;
        this.tags = tags;
        this.answers = answers;
    }

    public Question() {

    }

    public Question(Integer id, User author, String title, String text, Timestamp timeStamp, String image, List<Answer> answers) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
        this.timeStamp = timeStamp;
        this.image = image;
        this.answers = answers;
    }


    public List<AnswerDTO> getAnswersDTO() {
        return this.getAnswers().stream()
                .map(answer -> {
                    return new AnswerDTO(answer.getId(),answer.getText(),answer.getTimeStamp(),
                            new UserDTO(this.getAuthor().getUserId(), this.getAuthor().getFirstName(), this.getAuthor().getLastName(), this.getAuthor().getRating()),
                            this.text);
                })
                .collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public List<VoteQuestion> getVotes() {
        return votes;
    }

    public void setVotes(List<VoteQuestion> votes) {
        this.votes = votes;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
