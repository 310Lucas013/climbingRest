package com.fon.luc.climbingRest.controller;

import com.fon.luc.climbingRest.model.Account;
import com.fon.luc.climbingRest.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class AccountControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void getAccountsAPI()
            throws Exception {
//        mvc.perform( MockMvcRequestBuilders
//                .get("/accounts")
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
        Account account = new Account("abc@gmail.com", "asdfjlasjdfl123123");

        List<Account> allAccounts = Arrays.asList(account);

        given(accountService.getAccounts()).willReturn(allAccounts);

        mvc.perform( MockMvcRequestBuilders
                .get("/accounts")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].email").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("abc@gmail.com"));
    }
}
