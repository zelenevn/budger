package com.budger.repositories;

import com.budger.data.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT r FROM Role r JOIN AccountRole ar ON r.id=ar.role.id JOIN Account a ON a.id=ar.account.id WHERE a.login=:login")
    List<Role> findByAccountLogin(
            @Param("login") String login
    );

    @Query("SELECT r FROM Role r WHERE r.title=:title")
    Optional<Role> findByTitle(
            @Param("title") String title
    );
}