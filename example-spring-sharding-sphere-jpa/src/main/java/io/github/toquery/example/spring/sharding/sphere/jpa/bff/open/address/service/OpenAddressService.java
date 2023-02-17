package io.github.toquery.example.spring.sharding.sphere.jpa.bff.open.address.service;

import io.github.toquery.example.spring.sharding.sphere.jpa.modules.address.service.AddressService;
import io.github.toquery.example.spring.sharding.sphere.modules.address.Address;
import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class OpenAddressService {


    private final AddressService addressService;


    public List<Address> list() {

        return addressService.list();
    }
}
