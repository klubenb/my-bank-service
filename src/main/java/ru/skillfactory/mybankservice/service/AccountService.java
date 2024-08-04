package ru.skillfactory.mybankservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.mybankservice.api.dto.account.UpdateBalanceDto;
import ru.skillfactory.mybankservice.exceptions.RecordNotFoundException;
import ru.skillfactory.mybankservice.persistence.entity.Account;
import ru.skillfactory.mybankservice.persistence.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository repository;

    @Transactional
    public Account createAccount(Account account) {
        log.info("Create account {}", account);
        if (account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Невозможно создать счет с отрицательным балансом");
        }
        return repository.save(account);
    }

    @Transactional(readOnly = true)
    public Account findById(UUID accountId) {
        log.info("Find account by id {}", accountId);
        return repository.findById(accountId)
                .orElseThrow(() -> new RecordNotFoundException("Не найден счет с id: " + accountId));
    }

    @Transactional
    public Account takeMoney(UpdateBalanceDto dto) {
        log.info("Take money {}", dto);
        var account = findById(dto.id());
        if (account.getBalance().compareTo(dto.amount()) >= 0) {
            account.setBalance(account.getBalance().subtract(dto.amount()));
            return repository.save(account);
        } else {
            throw new IllegalArgumentException("Недостаточно средств на счете");
        }
    }

    @Transactional
    public Account putMoney(UpdateBalanceDto dto) {
        log.info("Put money {}", dto);
        var account = findById(dto.id());
        if (dto.amount().compareTo(BigDecimal.ZERO) > 0) {
            account.setBalance(account.getBalance().add(dto.amount()));
            return repository.save(account);
        } else {
            throw new IllegalArgumentException("Невозможно положить отрицательную сумму на счет");
        }
    }
}