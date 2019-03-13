package com.zenika.FormZenika_QA;


import com.zenika.FormZenika_QA.controller.FormulaireController;
import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.Question;
import com.zenika.FormZenika_QA.repository.FormulaireRepository;
import com.zenika.FormZenika_QA.repository.QuestionRepository;
import com.zenika.FormZenika_QA.service.FormulaireService;
import com.zenika.FormZenika_QA.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormZenikaQAApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FormControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private FormulaireService formulaireService;

    @Autowired
    private FormulaireRepository formulaireRepository;

    @Autowired
    private FormulaireController formulaireController;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void should_get_http_status_code_OK_from_given_url_get_mapping() {

        // GIVEN
        String getUrlForm = "http://localhost:" + port + "/forms";

        // WHEN
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(getUrlForm, String.class);
        String responseEntityBody = responseEntity.getBody();

        // THEN
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println("responseEntityBody = " + responseEntityBody);
    }

    @Test
    public void check_if_Form_saved_in_Database()
    {
        //Given
        List<Question> questions =new ArrayList<>();
        questions.add(new Question("how are you ?"));
        Formulaire formulaire =new Formulaire("form1",questions);


        //when
        formulaireService.save(formulaire);
        Optional<Formulaire> foundForm = formulaireRepository.findById(formulaire.getId());
        //Then
        assertThat(foundForm.get().getTitre()).isEqualTo(formulaire.getTitre());
        assertThat(foundForm.get().getId()).isEqualTo(formulaire.getId());

    }


}



