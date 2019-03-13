package com.zenika.FormZenika_QA.repository;

import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "from Question q  where  q.contenu like :x")
    Page<Question> chercher(@Param("x") String mc, Pageable pageable);

    List<Question> findByFormulaire(Formulaire formulaire);

    Optional<Question> findByIdAndFormulaireId(Long idQues, Long idForm);

    void deleteByFormulaire(Formulaire formulaire);

}