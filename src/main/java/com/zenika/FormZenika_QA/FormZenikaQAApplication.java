package com.zenika.FormZenika_QA;

import com.zenika.FormZenika_QA.model.*;
import com.zenika.FormZenika_QA.repository.*;
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
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        AnswerRespository answerRespository = ctx.getBean(AnswerRespository.class);
        RoleRepository roleRepository = ctx.getBean(RoleRepository.class);
        WebMvcConfig webMvcConfig = new WebMvcConfig();
        BCryptPasswordEncoder bCryptPasswordEncoder = webMvcConfig.passwordEncoder();

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


        Role userRole = roleRepository.findByRole("ADMIN");

        List<User> users = new ArrayList<>();


        User user = new User("redouaneelalami0@gmail.com","12345","redouane","elalami",1);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        Formulaire formulaire1 = new Formulaire();
        fr.save(formulaire1);
        user.setFormulaire(formulaire1);
        users.add(user);
    //    userRepository.save(user);

        Role userRole2 = roleRepository.findByRole("USER");

        /*User user2 = new User("redouaneelalami20@gmail.com","12345","redouane2","elalami2",1);
        user2.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user2.setRoles(new HashSet<>(Arrays.asList(userRole2)));
        user2.setFormulaire(formulaire);
        users.add(user2)*/;

        List<Answer> answerUser = new ArrayList<>();
        List<Answer> answerUser2 = new ArrayList<>();
        List<Answer> answersUser1 = new ArrayList<>();
        user.setAnswers(answerUser2);
       // user2.setAnswers(answerUser);

        List<Question> formulaireQuestions = formulaire.getQuestions();
        int i =0;

        for(Question question : formulaireQuestions)
        {
            i++;
          //  answerUser.add(new Answer(String.valueOf(i),question,user2));
            answerUser2.add(new Answer(String.valueOf(i+2),question,user));

        //    answersForOneQuestion.add(new Answer(String.valueOf(i),question,user2));
           // question.setAnswers(answersQuestions);
        }


       // userRepository.save(user2);
        userRepository.save(user);

       // List<Answer> answerByUser2 = answerRespository.findByUser(user2);
      //  System.out.println("answerByUser2 = " + answerByUser2);






       /* String desciption2 = "Le Labs, nouveau pôle de Zenika depuis un an, propose aux entreprises " +
                "une équipe “clé en main” (composée de 2 Développeurs, un UX/UI designer et un Product owner) pour kickstarter des projets et co-construire des MVP (Minimum Viable Product) sur une durée courte (jusqu’à 6 mois)" +
                " avec un pilotage inspiré des approches agiles Lean StartUp, Lean UX et du mouvement Software Craftsmanship.  \n" +
                "\n";
        Answer anQ = new Answer("Riennnnnnn");
        q10.setAnswer(anQ);
        Formulaire formulaire1 = new Formulaire("Zenika Labs", desciption2, questions2);
      //  User user2 = new User("aaaa@gmail.com","12345","aaaa","aaaa",1,formulaire1);
       // user2.setPassword(bCryptPasswordEncoder.encode(user2.getPassword()));
        Role userRole2 = roleRepository.findByRole("ADMIN");
      //  user2.setRoles(new HashSet<>(Arrays.asList(userRole2)));
        List<User> users = new ArrayList<>();
      //  users.add(user2);
       *//* formulaire1.setUsers(users);
        user2.setFormulaire(formulaire1);*//*

        List<Answer> answers = new ArrayList<>();
        answers.lastIndexOf(anQ);
      //  user2.setAnswers(answers);
        fr.save(formulaire1);
      //  userRepository.save(user2);
        //	fr.save(new Formulaire(questionRepository.findAll()));



    *//*    UserRepository userRepository = ctx.getBean(UserRepository.class);
        List<User> user = userRepository.findUsersByRoles();
        System.out.println("user = " + user);*//*
*//*
        UserRepository userRepository = ctx.getBean(UserRepository.class);
        List<User> role_user = userRepository.findByRolesss(2l);
        System.out.println("role_user = " + role_user);*//*
*//*

        UserRepository userRepository = ctx.getBean(UserRepository.class);
        List<User> userList = userRepository.findAll();
        for(User user : userList)
        {
            user.setFormulaire(formulaire);
        }
        formulaire.setUsers(userList);
*//*




        Role userRole = roleRepository.findByRole("ADMIN");


        User user = new User("redouaneelalami0@gmail.com","12345","redouane","elalami",1,formulaire);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        users.add(user);

        User user3 = new User("zzz@gmail.com","12345","zzz","zz",1,formulaire);
        user3.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user3.setRoles(new HashSet<>(Arrays.asList(userRole)));
        users.add(user3);


        formulaire.setUsers(users);
        userRepository.save(user);
        userRepository.save(user3);


        Answer an = new Answer("SII");
        Question question = new Question("Zenika ?", an);
        an.setQuestion(question);
        an.setUser(user);


        Answer an2 = new Answer("user3 reponses");
        Question question2 = new Question("user3 question ?", an2);
        an2.setQuestion(question2);
        an2.setUser(user3);


        // qr.save(question);
        List<Question> questionList = new ArrayList<>();
        questionList.add(question);
        questionList.add(question2);
        fr.save(new Formulaire("formualire Question Response","test redouane for answers",questionList));


*/
    }
}

