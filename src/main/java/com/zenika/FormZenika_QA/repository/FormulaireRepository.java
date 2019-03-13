package com.zenika.FormZenika_QA.repository;

import com.zenika.FormZenika_QA.model.Formulaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaireRepository extends JpaRepository<Formulaire, Long> {
    @Query(value = "from Formulaire f where f.titre like :x")
    Page<Formulaire> chercher(@Param("x") String mc, Pageable pageable);
}
