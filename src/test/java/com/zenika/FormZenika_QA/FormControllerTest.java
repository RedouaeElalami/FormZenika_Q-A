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

/*
    @Test @Ignore
    public void should_show_the_board_and_move_a_pawn() {

        // GIVEN
        String getUrlGame = "http://localhost:" + port + "/game";

        // WHEN
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(getUrlGame, String.class);
//        String responseEntityBody = responseEntity.getBody();

        String departure = "11";
        String destination = "22";

        ActionPlayer actionPlayer = new ActionPlayer(departure, destination);

        // // POST
        formulaireController.movePawn(actionPlayer);


        // THEN
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntityBody).contains("img/CASE_PION_NOIRE.png");

        BoardGame boardGame = formulaireRepository.getBoardGame();
        List<Pawn> pawns = boardGame.getPawns();


        PawnPosition endPosition
                = new PawnPosition(Short.valueOf(destination.substring(0, 1)), Short.valueOf(destination.substring(1, 2)));
        Optional<Pawn> optionalMovedPawn = pawns.stream()
                .filter(pawn -> pawn.getPosition().equals(endPosition))
                .findFirst();

        Pawn resultingPawn = optionalMovedPawn.orElse(null);
        System.out.println("resultingPawn = " + resultingPawn);
        assertThat(resultingPawn).isNotNull();

        PawnPosition startingPosition
                = new PawnPosition(Short.valueOf(departure.substring(0, 1)), Short.valueOf(departure.substring(1, 2)));

        Optional<Pawn> optionalEmptyPawn = pawns.stream()
                .filter(pawn -> pawn.getPosition().equals(startingPosition))
                .findFirst();

        Pawn resultingNullPawn = optionalEmptyPawn.orElse(null);
        assertThat(resultingNullPawn).isNull();
    }
*/


/*
    // Fonctionne avec @RequestBody, mais l'appli ne fonctionne plus si on l'utilise
    @Ignore
    @Test
    public void should_show_the_board_and_move_a_pawn_web() {

        // GIVEN
        String getUrlGame = "http://localhost:" + port + "/game";
        String postUrlMovePawn = "http://localhost:" + port + "/movePawn";

        // WHEN

        // GET
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(getUrlGame, String.class);
        String responseEntityBody = responseEntity.getBody();

        // POST
        PawnPosition startingPosition = new PawnPosition(Short.valueOf("1"), Short.valueOf("1"));
        PawnPosition endPosition = new PawnPosition(Short.valueOf("2"), Short.valueOf("2"));
        ActionPlayer actionPlayer = new ActionPlayer(startingPosition
                , endPosition);
        // Bricolage
        actionPlayer.setDeparture("11");
        actionPlayer.setDestination("22");

        testRestTemplate.postForEntity(postUrlMovePawn, actionPlayer, ActionPlayer.class);

        // THEN

        // GET
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntityBody).contains("img/CASE_PION_NOIRE.png");

        // POST
        BoardGame boardGame = formulaireRepository.getBoardGame();

        List<Pawn> pawns = boardGame.getPawns();

        Optional<Pawn> optionalMovedPawn = pawns.stream()
                .filter(pawn -> pawn.getPosition().equals(endPosition))
                .findFirst();

        Pawn resultingPawn = optionalMovedPawn.orElse(null);
        System.out.println("resultingPawn = " + resultingPawn);
        assertThat(resultingPawn).isNotNull();

*/
/*
        Optional<Pawn> optionalMovedPawn = pawns.stream()
                .filter(pawn -> pawn.getPosition().equals(endPosition))
                .findFirst();

        Pawn resultingPawn = optionalMovedPawn.orElse(null);
        assertThat(resultingPawn).isNotNull();
*//*



    }
*/
}



