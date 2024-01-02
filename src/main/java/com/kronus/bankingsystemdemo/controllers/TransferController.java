package com.kronus.bankingsystemdemo.controllers;

import com.kronus.bankingsystemdemo.dto.TransferRequest;
import com.kronus.bankingsystemdemo.models.Account;
import com.kronus.bankingsystemdemo.services.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest request) {
        transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount()
        );
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(@RequestParam(required = false) String name) {
        if (name == null) {
            return transferService.getAllAccounts();
        } else {
            return transferService.findAccountsByName(name);
        }
    }
}