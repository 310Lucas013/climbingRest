package com.fon.luc.climbingRest.repository;

import com.fon.luc.climbingRest.model.Account;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class AccountRepositoryIntegrationTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void GetAccountByEmail() {
        // given
        Account account = new Account("abc@gmail.com", "asdf123asdfweea");
        entityManager.persist(account);
        entityManager.flush();

        // when
        Account found = accountRepository.getAccountByEmail(account.getEmail());

        // then
        assertEquals(account.getUid(), found.getUid());
    }
}
