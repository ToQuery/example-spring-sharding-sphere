package io.github.toquery.example.spring.sharding.sphere.modules.account.repository;

import io.github.toquery.example.spring.sharding.sphere.core.modules.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long userId);

    Account getByUserId(Long userId);
}
