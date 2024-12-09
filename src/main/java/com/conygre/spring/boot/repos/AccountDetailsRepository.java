package com.conygre.spring.boot.repos;

import com.conygre.spring.boot.entities.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Integer> {

    Optional<AccountDetails> findByUserId(Integer userId);
}
