package io.github.toquery.example.spring.sharding.sphere.jpa.modules.account.service;

import io.github.toquery.example.spring.sharding.sphere.modules.account.Account;
import io.github.toquery.example.spring.sharding.sphere.jpa.modules.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;


    public Account save(Long userId) {
        Account account = new Account();
        account.setUserId(userId);
        account.setAccountStatus("active");
        return accountRepository.saveAndFlush(account);
    }

    public Account save(Account account) {
        return accountRepository.saveAndFlush(account);
    }

    public Account update(Account account) {
        return accountRepository.saveAndFlush(account);
    }

    public List<Account> list() {
        return accountRepository.findAll();
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account getByUserId(Long userId) {
        return accountRepository.getByUserId(userId);
    }

    public List<Account> findByUserId(Long userId) {

        return accountRepository.findByUserId(userId);
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }


}
