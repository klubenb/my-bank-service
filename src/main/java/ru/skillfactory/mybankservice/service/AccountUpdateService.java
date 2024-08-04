package ru.skillfactory.mybankservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.mybankservice.api.dto.account.TransferMoneyRequestDto;
import ru.skillfactory.mybankservice.api.dto.account.UpdateBalanceDto;
import ru.skillfactory.mybankservice.persistence.entity.Account;
import ru.skillfactory.mybankservice.persistence.entity.OperationHistory;
import ru.skillfactory.mybankservice.persistence.enumeration.OperationType;

@Service
@RequiredArgsConstructor
public class AccountUpdateService {

    private final AccountService accountService;
    private final OperationHistoryService operationService;

    @Transactional
    public Account takeMoney(UpdateBalanceDto dto) {
        var currentBalance = accountService.findById(dto.id()).getBalance();
        var account = accountService.takeMoney(dto);

        var operation = OperationHistory.builder()
                .account(account)
                .oldAmount(currentBalance)
                .newAmount(account.getBalance())
                .type(OperationType.TAKE_MONEY)
                .build();
        operationService.save(operation);
        return account;
    }

    @Transactional
    public Account putMoney(UpdateBalanceDto dto) {
        var currentBalance = accountService.findById(dto.id()).getBalance();
        var account = accountService.putMoney(dto);

        var operation = OperationHistory.builder()
                .account(account)
                .oldAmount(currentBalance)
                .newAmount(account.getBalance())
                .type(OperationType.PUT_MONEY)
                .build();
        operationService.save(operation);
        return account;
    }

    @Transactional
    public Account transferMoney(TransferMoneyRequestDto dto) {
        var currentBalanceFrom = accountService.findById(dto.accountFrom()).getBalance();
        var accountFrom = accountService.takeMoney(new UpdateBalanceDto(dto.accountFrom(), dto.amount()));

        var currentBalanceTo = accountService.findById(dto.accountTo()).getBalance();
        var accountTo = accountService.putMoney(new UpdateBalanceDto(dto.accountTo(), dto.amount()));

        var operationFrom = OperationHistory.builder()
                .account(accountFrom)
                .oldAmount(currentBalanceFrom)
                .newAmount(accountFrom.getBalance())
                .type(OperationType.TRANSFER_MONEY)
                .build();
        operationService.save(operationFrom);

        var operationTo = OperationHistory.builder()
                .account(accountTo)
                .oldAmount(currentBalanceTo)
                .newAmount(accountTo.getBalance())
                .type(OperationType.TRANSFER_MONEY)
                .transferFromAccountId(accountFrom)
                .build();
        operationService.save(operationTo);

        return accountFrom;
    }
}
