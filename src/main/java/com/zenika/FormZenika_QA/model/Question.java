package com.zenika.FormZenika_QA.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "questions")
public class Question implements Cloneable, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    @Lob
    private String contenu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_form")
    private Formulaire formulaire;

    @OneToMany()
    @JoinColumn(name = "question_id")
    private List<Answer> answers;

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


    public Question(List<Answer> answers) {
        this.answers = answers;
    }

    public Question(Formulaire formulaire, List<Answer> answers) {
        this.formulaire = formulaire;
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

}
