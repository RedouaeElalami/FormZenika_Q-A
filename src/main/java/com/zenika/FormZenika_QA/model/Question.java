package com.zenika.FormZenika_QA.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    @Lob
    private String contenu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_FORM", insertable = false, updatable = false)
    private Formulaire formulaire;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Answer answer;

    public Question(@NotNull @NotBlank String contenu, Formulaire formulaire, Answer answer) {
        this.contenu = contenu;
        this.formulaire = formulaire;
        this.answer = answer;
    }

    public Question(@NotNull @NotBlank String contenu, Answer answer) {
        this.contenu = contenu;
        this.answer = answer;
    }

    public Question() {
    }

    public Question(@NotNull @NotBlank String contenu, Formulaire formulaire) {
        this.contenu = contenu;
        this.formulaire = formulaire;
    }

    public Question(String question) {
        this.contenu = question;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Formulaire getFormulaire() {
        return formulaire;
    }

    public void setFormulaire(Formulaire formulaire) {
        this.formulaire = formulaire;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(contenu, question.contenu) &&
                Objects.equals(formulaire, question.formulaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contenu, formulaire);
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }


}
