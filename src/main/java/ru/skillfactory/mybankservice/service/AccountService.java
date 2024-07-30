package ru.skillfactory.mybankservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.mybankservice.api.dto.UpdateBalanceDto;
import ru.skillfactory.mybankservice.persistence.entity.Account;
import ru.skillfactory.mybankservice.persistence.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    @Transactional
    public Account createAccount(Account account) {
        if (account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Невозможно создать счет с отрицательным балансом");
        }
        return repository.save(account);
    }

    @Transactional(readOnly = true)
    public Account getBalance(UUID accountId) {
        return repository.findById(accountId);
    }

    @Transactional
    public BigDecimal takeMoney(UpdateBalanceDto dto) {
        var account = repository.findById(dto.id());
        if (account.getBalance().compareTo(dto.amount()) >= 0) {
            account.setBalance(account.getBalance().subtract(dto.amount()));
            repository.save(account);
            return dto.amount();
        } else {
            throw new IllegalArgumentException("Недостаточно средств на счете");
        }
    }

    @Transactional
    public BigDecimal putMoney(UpdateBalanceDto dto) {
        var account = repository.findById(dto.id());
        if (dto.amount().compareTo(BigDecimal.ZERO) > 0) {
            account.setBalance(account.getBalance().add(dto.amount()));
            repository.save(account);
            return account.getBalance();
        } else {
            throw new IllegalArgumentException("Невозможно положить отрицательную сумму на счет");
        }
    }
}