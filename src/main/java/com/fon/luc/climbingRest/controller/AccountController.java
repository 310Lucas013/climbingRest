package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.formData.UpdateAccount;
import com.fon.luc.climbingRest.model.Account;
import com.fon.luc.climbingRest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        Account complete = new Account(account.getEmail(), account.getUid());
        return new ResponseEntity<Account>(accountService.createAccount(complete), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAccount(@RequestBody UpdateAccount updateAccount) {
        Account account = accountService.updateAccount(updateAccount);
        return new ResponseEntity<>("Account is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value= "/{email}", method = RequestMethod.GET)
    public Account getAccountByEmail(@PathVariable("email") String email) {
        return accountService.findByEmail(email);
    }

}
