package com.zenika.FormZenika_QA.repository;

import com.zenika.FormZenika_QA.model.Role;
import com.zenika.FormZenika_QA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

}
