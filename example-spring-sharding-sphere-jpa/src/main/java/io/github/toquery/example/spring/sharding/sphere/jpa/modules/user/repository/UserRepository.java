package io.github.toquery.example.spring.sharding.sphere.jpa.modules.user.repository;

import io.github.toquery.example.spring.sharding.sphere.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
