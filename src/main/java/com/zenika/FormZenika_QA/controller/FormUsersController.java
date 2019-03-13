package com.zenika.FormZenika_QA.controller;

import com.zenika.FormZenika_QA.model.Answer;
import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.User;
import com.zenika.FormZenika_QA.repository.AnswerRespository;
import com.zenika.FormZenika_QA.repository.FormulaireRepository;
import com.zenika.FormZenika_QA.repository.UserRepository;
import com.zenika.FormZenika_QA.service.FormulaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class FormUsersController
{
    @Autowired
    private FormulaireService formulaireService;

    @Autowired
    private FormulaireRepository formulaireRepository;

    @Autowired
    private AnswerRespository answerRespository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("admin/formsUsers")
    public String allForms(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "20") int size,
                           @RequestParam(name = "mc", defaultValue = "") String mc)
    {
        Page<Formulaire> formulairePage = formulaireService.getAllFormulaires(page, size, mc);
        model.addAttribute("formulaires", formulairePage.getContent());
        int[] pages = new int[formulairePage.getTotalPages()];
        model.addAttribute("PagesOfFormd", pages);
        model.addAttribute("sizeOfForm", size);
        model.addAttribute("pageCourantOfForm", page);
        model.addAttribute("motCleOfForm", mc);
        return "allFormsForAllUsers";
    }

    @GetMapping("/admin/forms/{idForm}/users")
    public String usersByFormulaire(@PathVariable("idForm") Long idForm, Model model)
    {
        Optional<Formulaire> formulaire = formulaireRepository.findById(idForm);
        List<User> userByForm = userRepository.findByFormulaire(formulaire.orElse(null));
        model.addAttribute("formlaireUsers",formulaire.get());
        model.addAttribute("usersByForm",userByForm);
        return "displayUsersByForm";
    }

    @GetMapping("/admin/form/{idForm}/user/{IdUser}")
    public String answersForEachUser(@PathVariable Long idForm,@PathVariable Long IdUser,Model model)
    {
        Formulaire formulaireOfUser = formulaireRepository.findById(idForm).get();
        User user = userRepository.findById(IdUser).get();
        List<Answer> answersByUser = answerRespository.findByUser(user);
        model.addAttribute("answersByUser",answersByUser);
        model.addAttribute("formOfUser",formulaireOfUser);
        model.addAttribute("userIn",user);


        return "answerByUser";
    }

    @GetMapping("/admin/formulaire/{idForm}/reponses")
    public String answersByFormulaire(@PathVariable Long idForm, Model model)
    {
        Formulaire formulaire = formulaireRepository.findById(idForm).get();
        List<User> usersOfFormulaire = userRepository.findByFormulaire(formulaire);
        Map<User,List<Answer>> usersAnswersMap = new HashMap<>();

        for (int i =0;i<usersOfFormulaire.size();i++)
        {
            List<Answer> answers = answerRespository.findByUser(usersOfFormulaire.get(i));
            usersAnswersMap.put(usersOfFormulaire.get(i),answers);
        }
        model.addAttribute("usersMapByFormulaire",usersAnswersMap);
        model.addAttribute("formualaireOfUsers",formulaire);

        return "answersByFormulaire";
    }
}
