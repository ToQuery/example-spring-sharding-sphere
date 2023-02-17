package io.github.toquery.example.spring.sharding.sphere.jpa.bff.open.address.controller;

import io.github.toquery.example.spring.sharding.sphere.jpa.bff.open.address.service.OpenAddressService;
import io.github.toquery.example.spring.sharding.sphere.jpa.modules.address.service.AddressService;
import io.github.toquery.example.spring.sharding.sphere.modules.address.Address;
import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Filter;

/**
 *
 */
@RestController
@RequestMapping("/open/address")
@RequiredArgsConstructor
public class OpenAddressController {

    private final OpenAddressService openAddressService;
    @GetMapping
    public List<Address> list(@RequestParam(value = "tenantCode",required = false, defaultValue = "0") Long tenantCode) {
        HintManager hintManager = HintManager.getInstance();
//        hintManager.addDatabaseShardingValue("tenant_code", "10001");
        hintManager.setDatabaseShardingValue(tenantCode);
        List<Address> addresses = openAddressService.list();
        hintManager.clearShardingValues();
        hintManager.close();
        return addresses;
    }
}
