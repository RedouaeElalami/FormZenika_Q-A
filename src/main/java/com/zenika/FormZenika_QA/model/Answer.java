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

    @ManyToOne
    private Question question;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Answer() {
    }

    public Answer(Long i, Question question) {
        this.id = i;
        this.question = question;
    }


    public Answer(Long id, String response) {
        this.id = id;
        this.response = response;
    }


    public Answer(String response, Question question) {
        this.response = response;
        this.question = question;
    }

    public Answer(String response) {
        this.response = response;
    }

    public Answer(@NotNull @NotBlank String response, Question question, User user) {
        this.response = response;
        this.question = question;
        this.user = user;
    }

    public Answer(User user) {
        this.user = user;
    }

    public Answer(@NotNull @NotBlank String response, User user) {
        this.response = response;
        this.user = user;
    }

    public Answer(Question question, User user) {
        this.question = question;
        this.user = user;
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

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", response='" + response + '\'' +
                ", questionn=" + question +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
