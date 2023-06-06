package com.example.demo.dto;

import java.sql.Timestamp;
import java.util.List;

public class QuestionDTO extends ContentDTO{

    private String title;

    private List<AnswerDTO> answers;

    public QuestionDTO(Integer id, String text, Timestamp creation, UserDTO author, String title, List<AnswerDTO> answers) {
        super(id, text,creation,author);
        this.title = title;
        this.answers = answers;
    }

    public QuestionDTO(Integer id, String text, Timestamp creation, UserDTO author, String title) {
        super(id, text,creation,author);
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answersList) {
        this.answers = answersList;
    }
}
