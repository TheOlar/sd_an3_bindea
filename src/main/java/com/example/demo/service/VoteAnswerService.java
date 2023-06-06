package com.example.demo.service;

import com.example.demo.entity.Answer;
import com.example.demo.entity.User;
import com.example.demo.entity.VoteAnswer;
import com.example.demo.entity.VoteQuestion;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VoteAnswerRepository;
import com.example.demo.repository.VoteQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class VoteAnswerService implements VoteService<VoteAnswer> {


    @Autowired
    private VoteAnswerRepository voteAnswerRepo;
    @Autowired
    private VoteQuestionRepository voteQuestionRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public VoteAnswer createVote(Long userId, Integer contentId, Integer vote) {
        Optional<User> author = userRepository.findById(userId);
        Optional<Answer> answer = answerRepository.findById(Long.valueOf(contentId));
        AtomicInteger ok= new AtomicInteger(1);
        VoteAnswer va = null;

        if(author.isPresent() && answer.isPresent()) {
            // logic to not vote twice or change vote
            List<VoteAnswer> votes = getAllVotesOfContent(contentId);
            votes.forEach(v -> {
                if(v.getUser()==author.get())
                    if(Objects.equals(v.getVote(), vote)) {
                        deleteVote(v.getVoteId());
                        ok.set(0);
                    }
                    else {
                        updateVote(v.getVoteId(), vote);
                        ok.set(0);
                    }
            });
            if(ok.get()==1) {
                VoteAnswer votea = new VoteAnswer();
                votea.setAnswer(answer.get());
                votea.setUser(author.get());
                votea.setVote(vote);
                va = this.voteAnswerRepo.save(votea);
            }

            // UPDATE SCORE content
            Answer a = answerRepository.findById(Long.valueOf(contentId)).get();
            a.setRating( getScoreOfContent(contentId));
            answerRepository.save(a);
            // UPDATE SCORE user
            User updateUser = userRepository.findById(userId).get();
            updateUser.setRating(getScoreOfUser(userId));
            userRepository.save(updateUser);

            User updateVotedUser = answer.get().getAuthor();
            float score1 = getScoreOfUser(updateVotedUser.getUserId());
            System.out.println(score1);
            System.out.println(updateVotedUser.getUserId());
            updateVotedUser.setRating(score1);
            userRepository.save(updateVotedUser);
        }
        return va;
    }

    @Override
    public List<VoteAnswer> getAllVotes() {
        return (List<VoteAnswer>) voteAnswerRepo.findAll();
    }

    @Override
    public List<VoteAnswer> getAllVotesOfContent(Integer questionId) {
        List<VoteAnswer> votes = new ArrayList<>();
        voteAnswerRepo.findAll().forEach(v -> {
            if (v.getAnswer().getId().equals(questionId))
                votes.add(v);
        });
        return votes;

    }

    public Integer getScoreOfContent(Integer questionId) {
        List<VoteAnswer> votes =  getAllVotesOfContent(questionId);
        System.out.println(votes);
        if(!votes.isEmpty())
            return votes.stream().map(VoteAnswer::getVote).reduce(0,Integer::sum);
        return 0;
    }

    public Float getScoreOfUser(Long u_id) {
        float answerScore = getAllAnswerVotesTowardsUser(u_id) == null ? 0 :
                getAllAnswerVotesTowardsUser(u_id).stream().map(vote -> {
                    int voteValue = vote.getVote();
                    if (voteValue == 1) {
                        return 5f;
                    } else if (voteValue == -1) {
                        return -2.5f;
                    } else {
                        return 0f;
                    }
                }).reduce(0f,Float::sum);

        float questionScore = getAllQuestionVotesTowardsUser(u_id) == null ? 0:
                getAllQuestionVotesTowardsUser(u_id).stream().map(vote -> {
                    int voteValue = vote.getVote();
                    if (voteValue == 1) {
                        return 2.5f;
                    } else if (voteValue == -1) {
                        return -1.5f;
                    } else {
                        return 0f;
                    }
                }).reduce(0f,Float::sum);

        float userAnswerScore = getAllVotesOfUser(u_id) == null ? 0 :
                getAllVotesOfUser(u_id).stream().map(vote -> {
                    int voteValue = vote.getVote();
                    if(voteValue == -1)
                        return -1.5f;
                    else return 0f;
                }).reduce(0f,Float::sum);

        return answerScore+questionScore+userAnswerScore;
    }

    @Override
    public List<VoteAnswer> getAllVotesOfUser(Long userId) {
        List<VoteAnswer> votes = new ArrayList<>();
        voteAnswerRepo.findAll().forEach(v -> {
            if (v.getUser().getUserId().equals(userId))
                votes.add(v);
        });
        return votes;
    }

    @Override
    public List<VoteAnswer> getAllAnswerVotesTowardsUser(Long userId) {
        List<VoteAnswer> votes = new ArrayList<>();
        voteAnswerRepo.findAll().forEach(v -> {
            if (Objects.equals(v.getAnswer().getAuthor().getUserId(), userId))
                votes.add(v);
        });
        return votes;
    }

    @Override
    public List<VoteQuestion> getAllQuestionVotesTowardsUser(Long userId) {
        List<VoteQuestion> votes = new ArrayList<>();
        voteQuestionRepo.findAll().forEach(v -> {
            if (Objects.equals(v.getQuestion().getAuthor().getUserId(), userId))
                votes.add(v);
        });
        return votes;
    }

    @Override
    public VoteAnswer readAnswer(Integer id) {
        Optional<VoteAnswer> vote =  voteAnswerRepo.findById(id);
        return vote.orElse(null);
    }

    @Override
    public VoteAnswer updateVote(Integer id, Integer vote) {
        Optional<VoteAnswer> oldVote =  voteAnswerRepo.findById(id);
        if(oldVote.isPresent()) {
            VoteAnswer updateVote = oldVote.get();
            if(!Objects.equals(vote, updateVote.getVote())) {
                updateVote.setVote(vote);
            }
            return voteAnswerRepo.save(updateVote);
        }
        return null;
    }

    @Override
    public void deleteVote(Integer id) {
        voteAnswerRepo.deleteById(id);
    }



//
//    @Autowired
//    private VoteAnswerRepository voteAnswerRepo;
//
//
//    @Autowired
//    UserRepository userRepository;
//
//
//    @Autowired
//    AnswerRepository answerRepository;
//
//
//    public VoteAnswer createVote(Long userId, Integer contentId, VoteAnswer vote) {
//        Optional<User> author = userRepository.findById(userId);
//        Optional<Answer> answer = answerRepository.findById(Long.valueOf(contentId));
//        if(author.isPresent() && answer.isPresent()) {
//            vote.setAnswer(answer.get());
//            vote.setUser(author.get());
//            this.voteAnswerRepo.save(vote);
//            return vote;
//        }
//        return null;
//    }
//
//
//    public List<VoteAnswer> getAllVotes() {
//        return (List<VoteAnswer>) voteAnswerRepo.findAll();
//    }
//
//
//    public List<VoteAnswer> getAllVotesOfContent(Integer questionId) {
//        return null;
//    }
//
//
//    public List<VoteAnswer> getAllVotesOfUser(Long userId) {
//        return null;
//    }
//
//
//    public List<VoteAnswer> getAllVotesTowardsUser(Long userId) {
//        List<VoteAnswer> votes = new ArrayList<>();
//        voteAnswerRepo.findAll().forEach(v -> {
//            if (Objects.equals(v.getAnswer().getAuthor().getUserId(), userId))
//                votes.add(v);
//        });
//        return votes;
//    }
//
//
//    public VoteAnswer readAnswer(Integer id) {
//        Optional<VoteAnswer> vote =  voteAnswerRepo.findById(id);
//        return vote.orElse(null);
//    }
//
//
//    public VoteAnswer updateVote(Integer id, Integer vote) {
//        Optional<VoteAnswer> oldVote =  voteAnswerRepo.findById(id);
//        if(oldVote.isPresent()) {
//            VoteAnswer updateVote = oldVote.get();
//            if(!Objects.equals(vote, updateVote.getVote())) {
//                updateVote.setVote(vote);
//            }
//            return voteAnswerRepo.save(updateVote);
//        }
//        return null;
//    }
//
//
//    public void deleteVote(Integer id) {
//        voteAnswerRepo.deleteById(id);
//    }



}
