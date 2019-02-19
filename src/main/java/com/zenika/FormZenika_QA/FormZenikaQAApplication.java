package com.zenika.FormZenika_QA;

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

@SpringBootApplication
public class FormZenikaQAApplication {
	public static void main(String[] args) {
		ApplicationContext ctx=
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
		Question q = new Question("age");
		Question qq= new Question("prenom");
		Question q3= new Question("nom");
		Question q4= new Question("adresse");
		Question q5= new Question("nationalité");
		Collection<Question> questions = new ArrayList<>();
		questions.add(q);
		questions.add(qq);
		questions.add(q3);
		questions.add(q4);
		questions.add(q5);
		fr.save(new Formulaire("titre1",questions));
		Question q10 = new Question("motivation ?");
		Question q11 = new Question("perspectives ?");
		Question q12= new Question("c'est quoi Zenika selon vous ?");
		Question q13 = new Question("c'est quoi POO ? ?");
		Collection<Question> questions2 = new ArrayList<>();
		questions2.add(q10);
		questions2.add(q11);
		questions2.add(q12);
		questions2.add(q13);
		fr.save(new Formulaire("titre2",questions2));
	//	fr.save(new Formulaire(questionRepository.findAll()));

	}
}

