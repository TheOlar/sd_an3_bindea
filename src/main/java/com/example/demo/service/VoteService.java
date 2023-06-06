package com.example.demo.service;

import com.example.demo.entity.VoteAnswer;
import com.example.demo.entity.VoteQuestion;

import java.util.List;

public interface VoteService<U> {


    U createVote(Long userId, Integer contentId, Integer vote);
    List<U> getAllVotes();
    List<U> getAllVotesOfContent(Integer contentId);

    List<VoteAnswer> getAllVotesOfUser(Long userId);

    List<VoteAnswer> getAllAnswerVotesTowardsUser(Long userId);

//    List<U> getAllVotesTowardsUser(Long userId);

    List<VoteQuestion> getAllQuestionVotesTowardsUser(Long userId);

    U readAnswer(Integer id);
    U updateVote(Integer id, Integer vote);


    void deleteVote(Integer id);
}
