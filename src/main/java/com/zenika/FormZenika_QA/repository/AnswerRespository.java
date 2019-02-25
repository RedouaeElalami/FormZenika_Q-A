package com.zenika.FormZenika_QA.repository;

import com.zenika.FormZenika_QA.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRespository extends JpaRepository<Answer,Long> {
}
