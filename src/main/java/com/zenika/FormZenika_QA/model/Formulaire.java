package com.zenika.FormZenika_QA.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Formulaire
{
    @Id
    @GeneratedValue
    @Column(name = "id_form")
    private Long id;

    @Column(name = "titre")
    private String titre;

    @Lob
    private String description;

    //@OneToMany(mappedBy = "formulaire",cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_FORM", nullable = false, updatable = false)
    private List<Question> questions;

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
}
