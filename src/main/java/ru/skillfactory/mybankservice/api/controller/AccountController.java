package ru.skillfactory.mybankservice.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillfactory.mybankservice.api.dto.BaseResponseDto;
import ru.skillfactory.mybankservice.api.dto.CreateAccountDto;
import ru.skillfactory.mybankservice.api.dto.UpdateBalanceDto;
import ru.skillfactory.mybankservice.mapper.AccountMapper;
import ru.skillfactory.mybankservice.service.AccountService;

import java.math.BigDecimal;
import java.util.UUID;

@Tag(name = "Ticket", description = "API по тикету")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService service;
    private final AccountMapper mapper;

    @PostMapping("/create")
    @ApiResponse(description = "Успешно", responseCode = "200")
    @Operation(summary = "Создать счет")
    public BaseResponseDto<UUID> createAccount(@RequestBody CreateAccountDto dto) {
        return BaseResponseDto.asSuccess(service.createAccount(mapper.toEntity(dto)).getId());
    }

    @GetMapping("/get-balance/{id}")
    @ApiResponse(description = "Успешно", responseCode = "200")
    @Operation(summary = "Получить баланс")
    public BaseResponseDto<BigDecimal> getBalance(@PathVariable UUID id) {
        return BaseResponseDto.asSuccess(service.getBalance(id).getBalance().doubleValue());
    }

    @PostMapping("/take-money")
    @ApiResponse(description = "Успешно", responseCode = "200")
    @Operation(summary = "Получить деньги")
    public BaseResponseDto<UUID> takeMoney(@RequestBody UpdateBalanceDto dto) {
        return BaseResponseDto.asSuccess(service.takeMoney(dto));
    }

    @PostMapping("/put-money")
    @ApiResponse(description = "Успешно", responseCode = "200")
    @Operation(summary = "Положить деньги")
    public BaseResponseDto<UUID> putMoney(@RequestBody UpdateBalanceDto dto) {
        return BaseResponseDto.asSuccess(service.putMoney(dto));
    }
}