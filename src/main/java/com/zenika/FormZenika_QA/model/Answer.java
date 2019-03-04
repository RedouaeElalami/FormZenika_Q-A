package com.zenika.FormZenika_QA.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Answer implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @NotNull
    @NotBlank
    private String response;

    @OneToOne(mappedBy = "answer")
    private Question question;

    public Answer() {
    }

    public Answer(Long i, Question question) {
        this.id = i;
        this.question = question;
    }

    public Question getQuestionn() {
        return question;
    }

    public void setQuestionn(Question questionn) {
        this.question = questionn;
    }

    public Answer(Long id, String response) {
        this.id = id;
        this.response = response;
    }


    public Answer(String response, Question question) {
        this.response = response;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", response='" + response + '\'' +
                ", questionn=" + question +
                '}';
    }
}
