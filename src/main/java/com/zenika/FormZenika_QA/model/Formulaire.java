package com.zenika.FormZenika_QA.model;

import javax.persistence.*;

import java.util.List;

@Entity
public class Formulaire {
    @Id
    @GeneratedValue
    @Column(name = "id_form")
    private Long id;

    @Column(name = "titre")
    private String titre;

    @Lob
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_form")
    private List<Question> questions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_form")
    private List<User> users;

    public Formulaire() {
    }

    public Formulaire(String titre, List<Question> questions) {
        this.titre = titre;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Formulaire(String titre, String description, List<Question> questions) {
        this.titre = titre;
        this.description = description;
        this.questions = questions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Formulaire(List<User> users) {
        this.users = users;
    }


}
