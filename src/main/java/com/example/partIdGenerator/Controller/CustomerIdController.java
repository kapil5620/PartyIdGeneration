package com.example.partIdGenerator.Controller;

import com.example.partIdGenerator.Entity.CustomerIdInput;
import com.example.partIdGenerator.Service.CustomerIdServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerIdController {

    private final CustomerIdServiceImpl partyIdService;

    //class receives an instance of CustomerIdServiceImpl via constructor injection
    public CustomerIdController(CustomerIdServiceImpl partyIdService) {
        this.partyIdService = partyIdService;
    }

    @Async
    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<String>> createRequest(@RequestBody CustomerIdInput customerIdInput) {
        log.info("Thread running: {}", Thread.currentThread().getName());
        log.info("The request payload: {} ", customerIdInput);
        return CompletableFuture.supplyAsync(() -> {
            log.info("Thread running inside CompletableFuture: {}", Thread.currentThread().getName());
            String customerIdSave = partyIdService.createPartyId(customerIdInput);
            return ResponseEntity.ok(customerIdSave);
        });
    }
}