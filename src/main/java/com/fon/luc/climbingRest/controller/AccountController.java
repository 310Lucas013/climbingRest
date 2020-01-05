package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.model.Account;
import com.fon.luc.climbingRest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/accounts")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public Account createAccount(@Valid @RequestBody Account account) {
        System.out.println(account.toString());
        Account complete = new Account(account.getEmail(), account.getUid());
        return accountService.createAccount(complete);
    }

}
