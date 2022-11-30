package io.github.toquery.example.spring.sharding.sphere.modules.address.repository;

import io.github.toquery.example.spring.sharding.sphere.modules.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUserId(Long userId);
}
