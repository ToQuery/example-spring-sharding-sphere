package io.github.toquery.example.spring.sharding.sphere.jpa.bff.open.address.service;

import io.github.toquery.example.spring.sharding.sphere.jpa.modules.address.service.AddressService;
import io.github.toquery.example.spring.sharding.sphere.modules.address.Address;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OpenAddressService {

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5000), new ThreadPoolExecutor.AbortPolicy());
    private final AddressService addressService;


    @SneakyThrows
    public List<Address> list() {



        List<Address> a = addressService.list();

        log.info("Address {} getCurrentTransactionName {} getCurrentTransactionIsolationLevel {}",
                a.size(),
                TransactionSynchronizationManager.getCurrentTransactionName(),
                TransactionSynchronizationManager.getCurrentTransactionIsolationLevel());

        CompletableFuture<List<Address>> listCompletableFuture = CompletableFuture.supplyAsync(() -> {
            HintManager hintManager = HintManager.getInstance();
//        hintManager.addDatabaseShardingValue("tenant_code", "10001");
            hintManager.setDatabaseShardingValue(1L);
            List<Address> addresses = addressService.list();

            log.info("Address {}  getCurrentTransactionName {} getCurrentTransactionIsolationLevel {}",
                    addresses.size(),
                    TransactionSynchronizationManager.getCurrentTransactionName(),
                    TransactionSynchronizationManager.getCurrentTransactionIsolationLevel());
            hintManager.clearShardingValues();
            hintManager.close();
            return addresses;
        }, executor);
        return listCompletableFuture.get();
    }
}
