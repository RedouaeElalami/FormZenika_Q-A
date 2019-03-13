package com.zenika.FormZenika_QA;


import com.zenika.FormZenika_QA.controller.FormulaireController;
import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.Question;
import com.zenika.FormZenika_QA.model.Role;
import com.zenika.FormZenika_QA.model.User;
import com.zenika.FormZenika_QA.repository.FormulaireRepository;
import com.zenika.FormZenika_QA.repository.QuestionRepository;
import com.zenika.FormZenika_QA.repository.RoleRepository;
import com.zenika.FormZenika_QA.security.configuration.WebMvcConfig;
import com.zenika.FormZenika_QA.service.FormulaireService;
import com.zenika.FormZenika_QA.service.QuestionService;
import com.zenika.FormZenika_QA.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

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

    @Test
    @Transactional
    public void check_save_a_user_in_Database()
    {
        //Given
        WebMvcConfig webMvcConfig = new WebMvcConfig();
        BCryptPasswordEncoder bCryptPasswordEncoder = webMvcConfig.passwordEncoder();

        User user = new User("user@gmail.com","12345","userName","userLastName",1);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole=roleRepository.findByRole("USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("checkTest"));
        Formulaire formulaire =new Formulaire("formTest",questions);
        user.setFormulaire(formulaire);

        //When
        formulaireService.save(formulaire);
        userService.saveUser(user);

        List<User> users = userService.findByFormulaire(formulaire);

        //Then
        User expected ;
        assertThat(users).contains(user);

    }


}



