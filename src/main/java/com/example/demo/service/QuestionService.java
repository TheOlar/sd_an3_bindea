package com.example.demo.service;


import com.example.demo.dto.AnswerDTO;
import com.example.demo.dto.QuestionAnswersDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import com.example.demo.entity.Tags;
import com.example.demo.entity.User;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {



  //  static Integer id = 10;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TagRepository tagRepository;

    public Question createQuestion(Long userId, Question questionDetails) {
        // if i would have userRepository here i would check for the userId and get it
        Optional<User> author = userRepository.findById(userId);
        if(author.isPresent()) {
            questionDetails.setAuthor(author.get());
           // questionDetails.setId(id++);
            questionDetails.setTimeStamp(new Timestamp(System.currentTimeMillis()));
            questionRepository.save(questionDetails);
            return questionDetails;
        }
        return null;
    }


    public List<QuestionDTO> getAllQuestions() {
        List<QuestionDTO> questions = new ArrayList<>();
        questionRepository.findAll().forEach(q->questions.add(
                new QuestionDTO(q.getId(),q.getText(),q.getTimeStamp(),new UserDTO(q.getAuthor().getUserId(), q.getAuthor().getFirstName(), q.getAuthor().getLastName(), q.getAuthor().getRating()), q.getTitle(),
                        q.getAnswersDTO())
        ));
        return questions;
    }


    public QuestionDTO readQuestion(Integer id) {
        Optional<Question> question = questionRepository.findById(Long.valueOf(id));
        if(question.isPresent()) {
            Question question1 = question.get();
            return new QuestionDTO(question1.getId(), question1.getText(),question1.getTimeStamp(), new UserDTO(question1.getAuthor().getUserId(), question1.getAuthor().getFirstName(),question1.getAuthor().getLastName(), question1.getAuthor().getRating()), question1.getTitle());
        }
        return null;
    }


    public QuestionAnswersDTO readQuestionAndAnswer(Integer id) {
        Optional<Question> question = questionRepository.findById(Long.valueOf(id));
        if(question.isPresent()) {
            Question question1 = question.get();
            QuestionDTO questionDTO  = new QuestionDTO(question1.getId(), question1.getText(),question1.getTimeStamp(),
                    new UserDTO(question1.getAuthor().getUserId(), question1.getAuthor().getFirstName(),question1.getAuthor().getLastName(), question1.getAuthor().getRating()),
                    question1.getTitle(),question1.getAnswersDTO());
            List<Answer> answers = question1.getAnswers();
            if(answers.isEmpty())
                return new QuestionAnswersDTO(questionDTO,null);
            else {
                List<AnswerDTO> answersDTO = new ArrayList<>();
                answers.forEach(a->answersDTO.add(new AnswerDTO(a.getId(),a.getText(),a.getTimeStamp(),new UserDTO(a.getAuthor().getUserId(), a.getAuthor().getFirstName(),a.getAuthor().getLastName(), a.getAuthor().getRating()))));
                return new QuestionAnswersDTO(questionDTO,answersDTO);
            }
        }
        return null;
    }
    private void updateTime(Question question) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        question.setUpdated(date);
    }

    public Question updateQuestion(Integer id, Question newQuestion) {
        Optional<Question> question = questionRepository.findById(Long.valueOf(id));
        if(question.isPresent()) {
            Question oldQ = question.get();
            if(newQuestion.getText()!=null) {
                oldQ.setText(newQuestion.getText());
                updateTime(oldQ);
            }
            if(newQuestion.getTitle()!=null)
            {
                oldQ.setTitle(newQuestion.getTitle());
                updateTime(oldQ);
            }
            if(newQuestion.getImage()!=null) {
                oldQ.setImage(newQuestion.getImage());
                updateTime(oldQ);
            }
            return questionRepository.save(oldQ);
        }
        return null;
    }



    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(Long.valueOf(id));

    }


    public void addTagToQuestion(Integer questionId, String tagTitle) {
        Optional<Question> question = this.questionRepository.findById(Long.valueOf(questionId));
        if(question.isPresent()) {
            // Check if the tag already exists
            Tags tag = tagRepository.findByTitle(tagTitle);
            if (tag == null) {
                // Tag does not exist, create a new one
                tag = new Tags();
                tag.setTitle(tagTitle);
                tagRepository.save(tag);
            }
            Question question1 = question.get();
            question1.getTags().add(tag);
            questionRepository.save(question1);
        }
    }



//    @Autowired
//    QuestionRepository questionRepository;
//
//    public List<Question> retrieveQuestion() {
//        return (List<Question>) questionRepository.findAll();
//    }
//
//
//    public Question retrieveQuestionById(Long id) {
//
//        Optional<Question> question = questionRepository.findById(id);
//
//        if(question.isPresent()) {
//            return question.get();
//        } else {
//            return null;
//        }
//    }
//
//
//    public void deleteQuestionById(long parseLong) {
//        questionRepository.deleteById(parseLong);
//
//
//    }
//
//
//
//    public boolean saveQuestion(Question question) {
//
//        Question a = questionRepository.save(question);
//
//        if(a!=null) {
//            return true;
//        }
//        return false;
//    }
//
//



}
