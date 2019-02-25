package com.zenika.FormZenika_QA.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Formulaire
{
    @Id
    @GeneratedValue
    @Column(name = "id_form")
    private Long id;

    @Column(name = "titre")
    private String titre;

    //@OneToMany(mappedBy = "formulaire",cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_FORM", nullable = false, updatable = false)
    private Collection<Question> questions;

    public Formulaire() {
    }

    public Formulaire(String titre, Collection<Question> questions) {
        this.titre = titre;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


}
