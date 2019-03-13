package com.zenika.FormZenika_QA.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "email")
    @Email(message = "*S'il vous plaît fournir un email valide")
    @NotEmpty(message = "*veuillez fournir une adresse email")
    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "*Votre mot de passe doit comporter au moins 5 caractères.")
    @NotEmpty(message = "*S'il vous plaît fournir votre mot de passe")
    private String password;
    @Column(name = "name")
    @NotEmpty(message = "*Veuillez fournir votre prénom")
    private String name;
    @Column(name = "last_name")
    @NotEmpty(message = "*Veuillez fournir votre nom")
    private String lastName;
    @Column(name = "active")
    private int active;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "id_form")
    @NotNull(message = "*Veuillez choisir un formulaire")
    private Formulaire formulaire;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "user_id")
    private List<Answer> answers;


    public User() {
    }

    public User(@Email(message = "*S'il vous plaît fournir un email valide") @NotEmpty(message = "*veuillez fournir une adresse email") String email, @Length(min = 5, message = "*Votre mot de passe doit comporter au moins 5 caractères.") @NotEmpty(message = "*S'il vous plaît fournir votre mot de passe") String password, @NotNull(message = "*Veuillez choisir un formulaire") Formulaire formulaire, List<Answer> answers) {
        this.email = email;
        this.password = password;
        this.formulaire = formulaire;
        this.answers = answers;
    }

    public User(@Email(message = "*S'il vous plaît fournir un email valide") @NotEmpty(message = "*veuillez fournir une adresse email") String email, @Length(min = 5, message = "*Votre mot de passe doit comporter au moins 5 caractères.") @NotEmpty(message = "*S'il vous plaît fournir votre mot de passe") String password, @NotEmpty(message = "*Veuillez fournir votre prénom") String name, @NotEmpty(message = "*Veuillez fournir votre nom") String lastName, int active) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public User(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, Formulaire formulaire) {
        this.email = email;
        this.formulaire = formulaire;
    }

    public Formulaire getFormulaire() {
        return formulaire;
    }

    public void setFormulaire(Formulaire formulaire) {
        this.formulaire = formulaire;
    }

    public User(@Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password, @NotEmpty(message = "*Please provide your name") String name, @NotEmpty(message = "*Please provide your last name") String lastName, int active, Formulaire formulaire) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.formulaire = formulaire;
    }

    public User(@Email(message = "*S'il vous plaît fournir un email valide") @NotEmpty(message = "*veuillez fournir une adresse email") String email, @Length(min = 5, message = "*Votre mot de passe doit comporter au moins 5 caractères.") @NotEmpty(message = "*S'il vous plaît fournir votre mot de passe") String password, @NotEmpty(message = "*Veuillez fournir votre prénom") String name, @NotEmpty(message = "*Veuillez fournir votre nom") String lastName, int active, Set<Role> roles, @NotNull(message = "*Veuillez choisir un formulaire") Formulaire formulaire) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.roles = roles;
        this.formulaire = formulaire;
    }

    public User(@Email(message = "*S'il vous plaît fournir un email valide") @NotEmpty(message = "*veuillez fournir une adresse email") String email, @Length(min = 5, message = "*Votre mot de passe doit comporter au moins 5 caractères.") @NotEmpty(message = "*S'il vous plaît fournir votre mot de passe") String password, @NotEmpty(message = "*Veuillez fournir votre prénom") String name, @NotEmpty(message = "*Veuillez fournir votre nom") String lastName, int active, Set<Role> roles, @NotNull(message = "*Veuillez choisir un formulaire") Formulaire formulaire, List<Answer> answers) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.roles = roles;
        this.formulaire = formulaire;
        this.answers = answers;
    }

    public User(@NotNull(message = "*Veuillez choisir un formulaire") Formulaire formulaire, List<Answer> answers) {
        this.formulaire = formulaire;
        this.answers = answers;
    }

    public User(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
