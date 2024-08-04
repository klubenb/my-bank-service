package ru.skillfactory.mybankservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.mybankservice.api.dto.account.UpdateBalanceDto;
import ru.skillfactory.mybankservice.persistence.entity.Account;
import ru.skillfactory.mybankservice.persistence.entity.OperationHistory;
import ru.skillfactory.mybankservice.persistence.entity.enumeration.OperationType;

@Service
@RequiredArgsConstructor
public class AccountUpdateService {

    private final AccountService accountService;
    private final OperationHistoryService operationService;

    @Transactional
    public Account takeMoney(UpdateBalanceDto dto) {
        var account = accountService.takeMoney(dto);

        var operation = OperationHistory.builder()
                .account(account)
                .amount(account.getBalance())
                .type(OperationType.TAKE_MONEY)
                .build();
        operationService.create(operation);
        return account;
    }

    @Transactional
    public Account putMoney(UpdateBalanceDto dto) {
        var account = accountService.putMoney(dto);

        var operation = OperationHistory.builder()
                .account(account)
                .amount(account.getBalance())
                .type(OperationType.PUT_MONEY)
                .build();
        operationService.create(operation);
        return account;
    }
}
