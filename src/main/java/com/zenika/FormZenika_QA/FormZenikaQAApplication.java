package com.zenika.FormZenika_QA;

import com.zenika.FormZenika_QA.model.*;
import com.zenika.FormZenika_QA.repository.FormulaireRepository;
import com.zenika.FormZenika_QA.repository.QuestionRepository;
import com.zenika.FormZenika_QA.repository.RoleRepository;
import com.zenika.FormZenika_QA.repository.UserRepository;
import com.zenika.FormZenika_QA.security.configuration.WebMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class FormZenikaQAApplication {

    public static void main(String[] args) {
        ApplicationContext ctx =
                SpringApplication.run(FormZenikaQAApplication.class, args);
		/*QuestionRepository questionRepository = ctx.getBean(QuestionRepository.class);
		questionRepository.save(new Question("Votre nom"));
		questionRepository.save(new Question("Votre prenom"));
		questionRepository.save(new Question("Votre age"));
		questionRepository.save(new Question("Votre adress"));
*/
		/*questionRepository.
				findAll()
				.forEach(q-> System.out.println("questionAttribute = " + q.getContenu()));*/

        FormulaireRepository fr = ctx.getBean(FormulaireRepository.class);
        QuestionRepository qr = ctx.getBean(QuestionRepository.class);
        Question q = new Question("Que savez-vous de nous ?");
        Question qq = new Question("Pourquoi souhaitez-vous quitter votre poste actuel ?");
        Question q3 = new Question("Qu’est-ce qui a suscité votre intérêt dans cette offre d’emploi ");
        Question q4 = new Question("Parlez-moi d’une situation où…");
        Question q5 = new Question("Quels résultats avez-vous obtenus dans vos précédentes expériences ?");
        List<Question> questions = new ArrayList<>();
        questions.add(q);
        questions.add(qq);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
        String description1="ZENIKA ACADEMY, l'école du numérique de Zenika ouvre ses portes  !\n" +
                          "Notre manifeste pédagogique porte une vision, une #ethique et une volonté de différenciation";
        Formulaire formulaire = new Formulaire("Zenika Acamdemy recrutement 2019",description1, questions);
        fr.save(formulaire);
        Question q10 = new Question("Quelle est la critique la plus constructive qu’on a été amené à vous faire ?");
        Question q11 = new Question("Si vous pouviez revenir dans le passé, feriez-vous différemment certaines choses ?");
        Question q12 = new Question("Quels sont vos axes d’amélioration ?");
        Question q13 = new Question("Où vous voyez-vous dans cinq ans ??");
        List<Question> questions2 = new ArrayList<>();
        questions2.add(q10);
        questions2.add(q11);
        questions2.add(q12);
        questions2.add(q13);

        String desciption2 = "Le Labs, nouveau pôle de Zenika depuis un an, propose aux entreprises " +
                "une équipe “clé en main” (composée de 2 Développeurs, un UX/UI designer et un Product owner) pour kickstarter des projets et co-construire des MVP (Minimum Viable Product) sur une durée courte (jusqu’à 6 mois)" +
                " avec un pilotage inspiré des approches agiles Lean StartUp, Lean UX et du mouvement Software Craftsmanship.  \n" +
                "\n";
        fr.save(new Formulaire("Zenika Labs",desciption2, questions2));
        //	fr.save(new Formulaire(questionRepository.findAll()));
        Answer an = new Answer("SII");
        Question question = new Question("Zenika ?", an);
        an.setQuestion(question);

       // qr.save(question);
        List<Question> questionList = new ArrayList<>();
        questionList.add(question);
        fr.save(new Formulaire("formualire Question Response","test redouane for answers",questionList));


    /*    UserRepository userRepository = ctx.getBean(UserRepository.class);
        List<User> user = userRepository.findUsersByRoles();
        System.out.println("user = " + user);*/
/*
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        List<User> role_user = userRepository.findByRolesss(2l);
        System.out.println("role_user = " + role_user);*/
/*

        UserRepository userRepository = ctx.getBean(UserRepository.class);
        List<User> userList = userRepository.findAll();
        for(User user : userList)
        {
            user.setFormulaire(formulaire);
        }
        formulaire.setUsers(userList);
*/
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        RoleRepository roleRepository = ctx.getBean(RoleRepository.class);


        WebMvcConfig webMvcConfig = new WebMvcConfig();

         BCryptPasswordEncoder bCryptPasswordEncoder = webMvcConfig.passwordEncoder();


        User user = new User("redouaneelalami0@gmail.com","12345","redouane","elalami",1,formulaire);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        List<User> users = new ArrayList<>();
        users.add(user);
        formulaire.setUsers(users);
        userRepository.save(user);


    }
}

