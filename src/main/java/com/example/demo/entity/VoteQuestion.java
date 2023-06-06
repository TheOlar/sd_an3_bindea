package com.example.demo.entity;



import jakarta.persistence.*;
import org.hibernate.annotations.Check;

@Entity
@Table(name="vote_question")
public class VoteQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Integer voteId;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "vote")
    private Integer vote;


    public VoteQuestion() {
    }

    public VoteQuestion(Integer voteId, Question question, User user, Integer vote) {
        this.voteId = voteId;
        this.question = question;
        this.user = user;
        this.vote = vote;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}
