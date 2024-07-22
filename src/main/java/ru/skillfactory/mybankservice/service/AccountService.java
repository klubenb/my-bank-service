package ru.skillfactory.mybankservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.mybankservice.persistence.entity.Account;
import ru.skillfactory.mybankservice.persistence.repository.AccountRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    @Transactional
    public UUID createAccount(Account account) {
        return repository.save(account).getId();
    }

    @Transactional(readOnly = true)
    public Double getBalance(UUID accountId) {
        return repository.findById(accountId).getBalance();
    }

    @Transactional
    public Double takeMoney(UUID accountId, Double amount) {

        var account = repository.findById(accountId);
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            repository.save(account);
            return account.getBalance();
        } else {
            throw new ArithmeticException("You do not have enough money to take money");
        }
    }

    @Transactional
    public Double putMoney(UUID accountId, double amount) {
        var account = repository.findById(accountId);
        if (amount >= 0) {
            account.setBalance(account.getBalance() + amount);
            repository.save(account);
            return account.getBalance();
        } else {
            throw new ArithmeticException("You do not have enough money to put money");
        }
    }
}