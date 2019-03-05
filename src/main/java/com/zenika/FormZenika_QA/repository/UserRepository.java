package com.zenika.FormZenika_QA.repository;

import com.zenika.FormZenika_QA.model.Role;
import com.zenika.FormZenika_QA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    User findByEmail(String Email);

   /* @Query("select u from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where ur.role-id like :x")
    List<User> findByRolesss(@Param("x") Long id);
*/
}
