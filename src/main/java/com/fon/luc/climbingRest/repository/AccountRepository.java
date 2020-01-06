package com.fon.luc.climbingRest.repository;

import com.fon.luc.climbingRest.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("FROM Account a " +
            "WHERE a.email = ?1")
    Account findByEmail(String email);
}