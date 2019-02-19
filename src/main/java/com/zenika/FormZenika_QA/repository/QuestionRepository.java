package com.zenika.FormZenika_QA.repository;

import com.zenika.FormZenika_QA.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository  extends JpaRepository<Question,Long> {

    @Query(value = "from Question q  where  q.contenu like :x")
    Page<Question> chercher(@Param("x") String mc, Pageable pageable);
 //   Page<Question> findByFormulaireId(Long formId,Pageable pageable);

    /*
    @Query(value = "from Question q INNER JOIN q.formulaire f where q.contenu like :x")
    Page<Question> chercher(@Param("x") String mc, Pageable pageable);
*/

    /*@Query(value = "from Question q where  q.contenu like :x and  q.formulaire.id=id")
    Page<Question> chercherr(@Param("x") String mc,Long id, Pageable pageable);*/
}