package com.example.demo.entity;



import jakarta.persistence.*;
import org.hibernate.annotations.Check;

@Entity
@Table(name="vote_answer")
public class VoteAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Integer voteId;

    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "vote")
    @Check(constraints = "vote = 1 OR vote = -1 OR vote = 0")
    private Integer vote;


    public VoteAnswer() {
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
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

    public VoteAnswer(Integer voteId, Answer answer, User user, Integer vote) {
        this.voteId = voteId;
        this.answer = answer;
        this.user = user;
        this.vote = vote;
    }
}
