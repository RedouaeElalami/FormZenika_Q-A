package com.zenika.FormZenika_QA;

import com.zenika.FormZenika_QA.model.Answer;
import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.Question;
import com.zenika.FormZenika_QA.repository.FormulaireRepository;
import com.zenika.FormZenika_QA.repository.QuestionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
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
        Collection<Question> questions = new ArrayList<>();
        questions.add(q);
        questions.add(qq);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
        Formulaire formulaire = new Formulaire("Zenika Acamdemy recrutement 2019", questions);
        fr.save(formulaire);
        Question q10 = new Question("Quelle est la critique la plus constructive qu’on a été amené à vous faire ?");
        Question q11 = new Question("Si vous pouviez revenir dans le passé, feriez-vous différemment certaines choses ?");
        Question q12 = new Question("Quels sont vos axes d’amélioration ?");
        Question q13 = new Question("Où vous voyez-vous dans cinq ans ??");
        Collection<Question> questions2 = new ArrayList<>();
        questions2.add(q10);
        questions2.add(q11);
        questions2.add(q12);
        questions2.add(q13);
        fr.save(new Formulaire("Zenika Labs", questions2));
        //	fr.save(new Formulaire(questionRepository.findAll()));
        Answer an = new Answer("SII");
        Question question = new Question("Zenika ?", an);
        an.setQuestion(question);

       // qr.save(question);
        List<Question> questionList = new ArrayList<>();
        questionList.add(question);
        fr.save(new Formulaire("formualire Question Response",questionList));


    }
}

