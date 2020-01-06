package com.fon.luc.climbingRest.service;

import com.fon.luc.climbingRest.model.Account;
import com.fon.luc.climbingRest.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> getAccounts() {return accountRepository.findAll(); }

    public Account findByEmail(String email) {return accountRepository.findByEmail(email);}

}
