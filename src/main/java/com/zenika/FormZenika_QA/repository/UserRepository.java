package com.zenika.FormZenika_QA.repository;

import com.zenika.FormZenika_QA.model.Formulaire;
import com.zenika.FormZenika_QA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    User findByEmail(String Email);
   List<User> findByFormulaire(Formulaire formulaire);
   void deleteByFormulaire(Formulaire formulaire);
}
