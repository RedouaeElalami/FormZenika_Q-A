package com.zenika.FormZenika_QA.repository;

import com.zenika.FormZenika_QA.model.Answer;
import com.zenika.FormZenika_QA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRespository extends JpaRepository<Answer,Long> {
    List<Answer> findByUser(User user);
}
