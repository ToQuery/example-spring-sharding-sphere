package io.github.toquery.example.spring.sharding.sphere.jpa.modules.address.service;

import io.github.toquery.example.spring.sharding.sphere.jpa.modules.address.repository.AddressRepository;
import io.github.toquery.example.spring.sharding.sphere.modules.address.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public List<Address> save(Long userId) {
        Address address = new Address();
        address.setUserId(userId);
        address.setAddressName("address" + userId);
        return addressRepository.saveAll(List.of(address));
    }
    public Address save(Address address) {
        return addressRepository.saveAndFlush(address);
    }
    public Address update(Address address) {
        return addressRepository.saveAndFlush(address);
    }


    public List<Address> list() {
        return addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> findByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    public void delete(Long id) {
        addressRepository.deleteById(id);
    }



}
