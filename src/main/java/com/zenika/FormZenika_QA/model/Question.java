package com.zenika.FormZenika_QA.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "questionTable")
public class Question
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_generator")
    @SequenceGenerator(name = "question_generator", sequenceName = "id_question_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3,max = 1000)
    private String contenu;

    @ManyToOne(fetch = FetchType.EAGER)
   //
    // @JoinColumn(name = "ID_FORM")
    @JoinColumn(name = "ID_FORM", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Formulaire formulaire;

    public Question() {
    }

    public Question(@NotNull @NotBlank @Size(min = 3, max = 1000) String contenu, Formulaire formulaire) {
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
}
