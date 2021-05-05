package com.data.dao;

import com.data.entity.Account;
import com.data.entity.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation,Integer> {

    Optional<PersonalInformation> findByAccount(Account account);
}
