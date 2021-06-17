package com.budger.repositories;

import com.budger.data.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT s FROM Account s WHERE s.login = :login")
    public Optional<Account> findByLogin(
            @Param("login") String login
    );

}
